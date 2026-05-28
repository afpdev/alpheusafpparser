# Metadata Object Content Architecture Reference

**AFPC-0013-02**

## Note
Before using this information, read the information in “Notices”. [MOCA-FM-001]

AFPC-0013-02
Second Edition (December 2024) [MOCA-FM-002]

This edition applies to the Metadata Object Content Architecture. It is the second edition produced by the AFP Consortium™ (AFPC™) and replaces and makes obsolete the previous edition (AFPC-0013-01) . This edition remains current until a new edition is published. [MOCA-FM-003]

Technical changes are indicated in green, with a green vertical bar to the left of the change. Editorial changes that have no technical significance are not noted. For a detailed list of changes, see “Changes in This Edition ”. [MOCA-FM-004]

**Internet**
Visit our home page, where this publication and others are available:
www.afpconsortium.org [MOCA-FM-005]

---

# Preface
This book describes the functions and services associated with the Metadata Object Content Architecture (MOCA). [MOCA-FM-006]

This book is a reference, not a tutorial. It complements individual product publications, but does not describe product implementations of the architecture. [MOCA-FM-007]

## Who Should Read This Book
This book is for system programmers and other developers who need such information to develop or adapt a product or program to interoperate with other presentation products in an Advanced Function Presentation™ (AFP™) environment. [MOCA-FM-008]

## AFP Consortium (AFPC)
The AFP Consortium is an international group bringing together voices from across the printing and presentation industry to keep the AFP architecture up to date and continually improving. AFP Consortium members, often market competitors, work together to ensure this stable, efficient, flexible architecture continues to thrive, even as the world of printing and presentation changes. [MOCA-FM-009]

The Advanced Function Presentation (AFP) architectures began as the strategic, general purpose document and information presentation architecture for the IBM® Corporation. The first specifications and products go back to 1984. Although all of the components of the architecture have grown over the years, the major concepts of object-driven structures, print integrity, resource management, and support for high print speeds were built in from the start. [MOCA-FM-010]

In the early twenty-first century, IBM saw the need to enable applications to create color output that is independent from the device used for printing and to preserve color consistency, quality, and fidelity of the printed material. This need resulted in the formation, in October 2004, of the AFP Color Consortium™ (AFPCC™). The goal was to extend the object architectures with support for full-color devices including support for comprehensive color management. The idea of doing this via a consortium consisting of the primary AFP architecture users was to build synergism with partners from across the relevant industries, such as hardware manufacturers that produce printers as well as software vendors of composition, work flow, viewer, and transform tools. Quickly more than 30 members came together in regular meetings and work group sessions to create the AFP Color Management Object Content Architecture™ (CMOCA™). A major milestone was reached by the AFP Color Consortium with the initial official release of the CMOCA specification in May 2006. [MOCA-FM-011]

Since the cooperation between the members of the AFP Color Consortium turned out to be very effective and valuable, it was decided to broaden the scope of the consortium efforts and IBM soon announced its plans to open up the complete scope of the AFP architecture to the consortium. In June 2007, IBM's role as founding member of the consortium was transferred to the InfoPrint® Solutions Company, an IBM/Ricoh® joint venture; currently Ricoh holds the founding member position . In February 2009, the consortium was incorporated under a new set of bylaws with tiered membership and shared governance resulting in the creation of a formal open standards body called the AFP Consortium (AFPC). Ownership of and responsibility for the AFP architectures was transferred at that time to the AFP Consortium. [MOCA-FM-012]

## How to Use This Book
This document is divided into five chapters:
* **Chapter 1, “A Presentation Architecture Perspective”** introduces the AFPC presentation architectures and describes the role of data streams and data objects. [MOCA-FM-013]
* **Chapter 2, “Introduction to MOCA”** introduces the goals and scope of the Metadata Object Content Architecture. [MOCA-FM-014]
* **Chapter 3, “Metadata Concepts”** discusses metadata concepts as they relate to AFP . [MOCA-FM-015]
* **Chapter 4, “Metadata Object (MO)”** specifies the MO syntax and semantics. [MOCA-FM-016]
* **Chapter 5, “MO Header Attributes”** registers and describes the supported MO types, formats, and compression. [MOCA-FM-017]
* **Chapter 6, “Compliance ”** describes the subsets that MOCA defines. [MOCA-FM-018]

The **“Glossary”** defines some of the terms used in this book. [MOCA-FM-019]

## How to Read the Syntax Diagrams
The basic data types used in the Metadata Object Content Architecture (MOCA) are:
* **UBIN** Unsigned binary [MOCA-FM-020]
* **UTF16** UTF-16BE characters [MOCA-FM-021]
* **UNDF** Undefined type [MOCA-FM-022]

The following notation conventions apply to the MO data structures.
* Each byte contains eight bits. [MOCA-FM-023]
* Bytes of an MO data structure are numbered beginning with byte 0. For example, a two-byte field followed by a one-byte field would be numbered as follows: [MOCA-FM-024]
  - Bytes 0–1 Field 1 [MOCA-FM-025]
  - Byte 2 Field 2 [MOCA-FM-026]
* Field values are expressed in hexadecimal or binary notation: [MOCA-FM-027]
  - X'7FFF' = +32,767 [MOCA-FM-028]
  - B'0001' = 1 [MOCA-FM-029]
* Some bits or bytes are labeled reserved. The content of reserved fields is not checked by MO receivers. However, MO generators should set reserved fields to the specified value. [MOCA-FM-030]

## Related Publications
Several other publications can help you understand the architecture concepts described in this book. AFP Consortium publications and a few other AFP publications are available on the AFP Consortium website, http://www.afpconsortium.org. [MOCA-FM-031]

### Table 1. AFP Consortium Architecture References
| AFP Architecture Publication | Order Number |
| :--- | :--- |
| AFP Programming Guide and Line Data Reference | AFPC-0010 [MOCA-FM-032]|
| Bar Code Object Content Architecture™ Reference | AFPC-0005 [MOCA-FM-033]|
| Color Management Object Content Architecture Reference | AFPC-0006 [MOCA-FM-034]|
| Font Object Content Architecture Reference | AFPC-0007 [MOCA-FM-035]|
| Graphics Object Content Architecture for Advanced Function Presentation Reference | AFPC-0008 [MOCA-FM-036]|
| Image Object Content Architecture Reference | AFPC-0003 [MOCA-FM-037]|
| Intelligent Printer Data Stream™ Reference | AFPC-0001 [MOCA-FM-038]|
| Metadata Object Content Architecture Reference | AFPC-0013 [MOCA-FM-039]|
| Mixed Object Document Content Architecture™ (MO:DCA™) Reference | AFPC-0004 [MOCA-FM-040]|
| Presentation Text Object Content Architecture Reference | AFPC-0009 [MOCA-FM-041]|

### Table 2. Additional AFP Consortium Documentation
| AFPC Publication | Order Number [MOCA-FM-042]|
| :--- | :--- |
| AFP Color Management Architecture™ (ACMA™) | G550-1046 (IBM) [MOCA-FM-043]|
| AFPC Company Abbreviation Registry | AFPC-0012 [MOCA-FM-044]|
| AFPC Font Typeface Registry | AFPC-0016 [MOCA-FM-045]|
| BCOCA™ Frequently Asked Questions | AFPC-0011 [MOCA-FM-046]|
| Metadata Guide for AFP | AFPC-0018 [MOCA-FM-047]|
| MO:DCA-L: The OS/2 PM Metafile (.met) Format | AFPC-0014 [MOCA-FM-048]|
| Presentation Object Subsets for AFP | AFPC-0002 [MOCA-FM-049]|
| Recommended IPDS™ Values for Object Container Versions | AFPC-0017 [MOCA-FM-050]|

### Table 3. Other Documentation
| Publication [MOCA-FM-051]|
| :--- |
| XMP™ (Extensible Metadata Platform) Specification, September 2005 [MOCA-FM-052]|
| Efficient XML Interchange (EXI) Format 1.0 [MOCA-FM-053]|
| RFC 1952 - GZIP file format specification version 4.3 [MOCA-FM-054]|

---

# Changes in This Edition
The following is a summary of the changes that have been made in this edition:
* AFP Tagging metadata format added [MOCA-FM-055]
* MS1 subset defined; the subset includes all functionality found in the first edition of the MOCA Reference [MOCA-FM-056]
  - “Compliance” chapter added to introduce the concept of subsets and to contain the MS1 subset definition [MOCA-FM-057]
* Discussion of mapping MOCA exception conditions to IPDS exceptions added [MOCA-FM-058]
* Discussion of how Metadata Objects are carried in IPDS added [MOCA-FM-059]

Technical changes between this edition and the previous edition are shown in green, with a green “|” revision bar in the left margin, as this text is. [MOCA-FM-060]

---

# Contents
- Preface [MOCA-FM-061]
  - Who Should Read This Book [MOCA-FM-062]
  - AFP Consortium (AFPC) [MOCA-FM-063]
  - How to Use This Book [MOCA-FM-064]
  - How to Read the Syntax Diagrams [MOCA-FM-065]
  - Related Publications [MOCA-FM-066]
- Changes in This Edition [MOCA-FM-067]
- Figures [MOCA-FM-068]
- Tables [MOCA-FM-069]
- Chapter 1. A Presentation Architecture Perspective [MOCA-FM-070]
- Chapter 2. Introduction to MOCA [MOCA-FM-071]
- Chapter 3. Metadata Concepts [MOCA-FM-072]
- Chapter 4. Metadata Object (MO) [MOCA-FM-073]
- Chapter 5. MO Header Attributes [MOCA-FM-074]
- Chapter 6. Compliance [MOCA-FM-075]
- Notices [MOCA-FM-076]
- Trademarks [MOCA-FM-077]
- Glossary [MOCA-FM-078]
- Index

---

# Figures
1. Presentation Environment [MOCA-FM-079]
2. Presentation Model [MOCA-FM-080]
3. Presentation Page [MOCA-FM-081]

---

# Tables
1. AFP Consortium Architecture References [MOCA-FM-082]
2. Additional AFP Consortium Documentation [MOCA-FM-083]
3. Other Documentation [MOCA-FM-084]
4. MO Syntax [MOCA-FM-085]
