# Color Management Object Content Architecture (CMOCA) Reference

**Advanced Function Presentation Consortium**
**Data Stream and Object Architectures**
**AFPC-0006-02**

---

**Copyright © AFP Consortium 2006, 2025**

**Note:** Before using this information, read the information in “Notices”. [CMOCA-FM-001]

**AFPC-0006-02**
**Third Edition (May 2025)** [CMOCA-FM-002]

This edition applies to the Color Management Object Content Architecture™ (CMOCA™). It is the second edition produced by the AFP Consortium™ (AFPC™) and replaces and makes obsolete the previous edition (AFPC-0006-01). This edition remains current until a new edition is published. This publication also applies to any subsequent releases of Advanced Function Presentation™ (AFP™) products that use the CMOCA architecture until otherwise indicated in a new edition. [CMOCA-FM-003]

Technical changes are indicated in green, with a green vertical bar to the left of the change. Editorial changes that have no technical significance are not noted. For a detailed list of changes, see “Changes in This Edition”. [CMOCA-FM-004]

**Internet:** Visit our home page: [www.afpconsortium.org](http://www.afpconsortium.org) [CMOCA-FM-005]

---

## Preface

This book describes the functions and services associated with the Color Management Object Content Architecture (CMOCA). This book is a reference, not a tutorial. It complements individual product publications, but does not describe product implementations of the architecture. [CMOCA-FM-006]

### Who Should Read This Book

This book is for system programmers and other developers who need such information to develop or adapt a product or program to interoperate with other presentation products in an Advanced Function Presentation (AFP) environment. [CMOCA-FM-007]

### AFP Consortium

The AFP Consortium is an international group bringing together voices from across the printing and presentation industry to keep the AFP architecture up to date and continually improving. AFP Consortium members, often market competitors, work together to ensure this stable, efficient, flexible architecture continues to thrive, even as the world of printing and presentation changes. [CMOCA-FM-008]

The Advanced Function Presentation (AFP) architectures began as the strategic, general purpose document and information presentation architecture for the IBM® Corporation. The first specifications and products go back to 1984. Although all of the components of the architecture have grown over the years, the major concepts of object-driven structures, print integrity, resource management, and support for high print speeds were built in from the start. [CMOCA-FM-009]

In the early twenty-first century, IBM saw the need to enable applications to create color output that is independent from the device used for printing and to preserve color consistency, quality, and fidelity of the printed material. This need resulted in the formation, in October 2004, of the AFP Color Consortium™ (AFPCC™). The goal was to extend the object architectures with support for full-color devices including support for comprehensive color management. The idea of doing this via a consortium consisting of the primary AFP architecture users was to build synergism with partners from across the relevant industries, such as hardware manufacturers that produce printers as well as software vendors of composition, work flow, viewer, and transform tools. Quickly more than 30 members came together in regular meetings and work group sessions to create the AFP Color Management Object Content Architecture (CMOCA). A major milestone was reached by the AFP Color Consortium with the initial official release of the CMOCA specification in May 2006. [CMOCA-FM-010]

Since the cooperation between the members of the AFP Color Consortium turned out to be very effective and valuable, it was decided to broaden the scope of the consortium efforts and IBM soon announced its plans to open up the complete scope of the AFP architecture to the consortium. In June 2007, IBM's role as founding member of the consortium was transferred to the InfoPrint® Solutions Company, an IBM/Ricoh® joint venture; currently Ricoh holds the founding member position. In February 2009, the consortium was incorporated under a new set of bylaws with tiered membership and shared governance resulting in the creation of a formal open standards body called the AFP Consortium (AFPC). Ownership of and responsibility for the AFP architectures was transferred at that time to the AFP Consortium. [CMOCA-FM-011]

## Publication History

The CMOCA Reference was first published by IBM in 2006 and has had several enhancements and updates since that time. [CMOCA-FM-012]

*   **First Edition published by IBM Corporation** [CMOCA-FM-013]
    S550-0511-00 dated May 2006
*   **Second Edition published by AFP Consortium** [CMOCA-FM-014]
    AFPC-0006-01 dated April 2012
    This previous edition provided enhanced detail to support the CMOCA products that were introduced in the years 2006 through 2011 and to support the work of the AFP Consortium. Specifically, the following new function and clarification was provided:
    *   Expanded range of valid values for MediaFinish and MediaColor and provide AFPC suggested values [CMOCA-FM-015]
    *   ICC DeviceLink CMR [CMOCA-FM-016]
    *   ICC Profile Filename tag [CMOCA-FM-017]
    *   Indexed CMR refinements including new exception codes X'12' and X'13' [CMOCA-FM-018]
    *   Media finish values for coated, commodity, newsprint, and treated media [CMOCA-FM-019]
    *   Partial Support of TTC & HT CMRs [CMOCA-FM-020]
    *   Passthrough CMR [CMOCA-FM-021]
    *   Registered AFP-Consortium-provided standard audit CMRs [CMOCA-FM-022]
    *   Removed Color Conversion CMR subset X'0A' (Abstract Profile) [CMOCA-FM-023]
    *   Clarifications for: [CMOCA-FM-024]
        *   Array Width and Array Height tag descriptions [CMOCA-FM-025]
        *   Custom values for media-finish and media-color fields in the CMR header [CMOCA-FM-026]
        *   Default audit CMRs color-space descriptions for grayscale and YCbCr/YCrCb [CMOCA-FM-027]
        *   Named and highlight colors plus a recommendation for OCA black [CMOCA-FM-028]
        *   Padding and use of device-specific values in the CMR header [CMOCA-FM-029]
        *   Removed EC-xxxx05 (Invalid Count Value) exception conditions that were covered by EC-xxxx11 (Inconsistent Tag Contents) [CMOCA-FM-030]

## How to Use This Book

This document is divided into six chapters and three appendixes: [CMOCA-FM-031]

*   **Chapter 1, “A Presentation Architecture Perspective”** introduces the AFP presentation architectures and describes the role of data streams and data objects. [CMOCA-FM-032]
*   **Chapter 2, “Introduction to CMOCA”** introduces the goals and purposes of the Color Management Object Content Architecture. [CMOCA-FM-033]
*   **Chapter 3, “Color Management Resource (CMR)”** discusses the format of the Color Management Resource (CMR) header and how CMRs are used to process data. [CMOCA-FM-034]
*   **Chapter 4, “CMR Types”** defines each type of CMR. [CMOCA-FM-035]
*   **Chapter 5, “CMR Data Architecture”** defines tag syntax and semantics. [CMOCA-FM-036]
*   **Chapter 6, “CMR Processing”** discusses how search paths are used to determine which CMRs to use, audit and instruction and link CMRs, generic vs. device-specific CMRs, and implications for drivers. [CMOCA-FM-037]
*   **Appendix A, “Tag Registry”** lists the tags that devices receiving CMRs must support. [CMOCA-FM-038]
*   **Appendix B, “Generic CMR Name Registry”** lists and explains all the registered generic CMR names. [CMOCA-FM-039]
*   **Appendix C, “Compliance With Color Management Object Content Architecture”** explains what is required to claim CMOCA support. [CMOCA-FM-040]

The **“Glossary”** defines some of the terms used within this book. [CMOCA-FM-041]

## Interpreting the Syntax

The basic data types used in the Color Management Object Content Architecture (CMOCA) are: [CMOCA-FM-042]

| Type | Description |
| :--- | :--- |
| **CODE** | Architected constant [CMOCA-FM-043]|
| **BITS** | Bit string [CMOCA-FM-044]|
| **UBIN** | Unsigned binary [CMOCA-FM-045]|
| **BYTE** | 8 bits [CMOCA-FM-046]|
| **ASCII** | ASCII-encoded characters [CMOCA-FM-047]|
| **UTF16** | UTF-16BE characters (2-bytes each) [CMOCA-FM-048]|
| **UNDF** | Undefined data type [CMOCA-FM-049]|

The following notation conventions apply to the CMR data structures. [CMOCA-FM-050]

*   Each byte contains eight bits. [CMOCA-FM-051]
*   Bytes of a CMR data structure are numbered from left to right beginning with byte 0. The left-most byte is the most significant; this is called big endian. For example, a two-byte field followed by a one-byte field would be numbered as follows: [CMOCA-FM-052]
    *   Bytes 0–1 Field 1 [CMOCA-FM-053]
    *   Byte 2 Field 2 [CMOCA-FM-054]
*   Bit strings are numbered from left to right beginning with 0. For example, a one-byte bit string contains bit 0, bit 1, …, bit 7. [CMOCA-FM-055]
*   For numerical binary data, bit 0 is the most significant bit. [CMOCA-FM-056]
*   Field values are expressed in hexadecimal or binary notation: [CMOCA-FM-057]
    *   X'7FFF' = +32,767 [CMOCA-FM-058]
    *   B'0001' = 1 [CMOCA-FM-059]
*   Some bits or bytes are labeled **reserved**. The content of reserved fields is not checked by CMR receivers. However, CMR generators should set reserved fields to the specified value, if one is given, or to zero. [CMOCA-FM-060]
*   Some fields or values are labeled **Retired item n**, where n is an identifying number. These fields or values are reserved for a particular purpose and must not be used for any other purpose. [CMOCA-FM-061]

## Related Publications

Several other publications can help you understand the architecture concepts described in this book. The AFP Consortium publications and other AFP publications below are available on the AFP Consortium website, [www.afpconsortium.org](http://www.afpconsortium.org). [CMOCA-FM-062]

### Table 1. AFP Consortium Architecture References

| AFP Architecture Publication | Book Identification |
| :--- | :--- |
| AFP Programming Guide and Line Data Reference | AFPC-0010 [CMOCA-FM-063]|
| Bar Code Object Content Architecture™ Reference | AFPC-0005 [CMOCA-FM-064]|
| Color Management Object Content Architecture Reference | AFPC-0006 [CMOCA-FM-065]|
| Font Object Content Architecture Reference | AFPC-0007 [CMOCA-FM-066]|
| Graphics Object Content Architecture for Advanced Function Presentation Reference | AFPC-0008 [CMOCA-FM-067]|
| Image Object Content Architecture Reference | AFPC-0003 [CMOCA-FM-068]|
| Intelligent Printer Data Stream™ Reference | AFPC-0001 [CMOCA-FM-069]|
| Metadata Object Content Architecture Reference | AFPC-0013 [CMOCA-FM-070]|
| Mixed Object Document Content Architecture™ (MO:DCA™) Reference | AFPC-0004 [CMOCA-FM-071]|
| Presentation Text Object Content Architecture Reference | AFPC-0009 [CMOCA-FM-072]|

### Table 2. Additional AFP Consortium Documentation

| AFPC Publication | Book Identification [CMOCA-FM-073]|
| :--- | :--- |
| AFP Color Management Architecture™ (ACMA™) | G550-1046 (IBM) [CMOCA-FM-074]|
| AFPC Company Abbreviation Registry | AFPC-0012 [CMOCA-FM-075]|
| AFPC Font Typeface Registry | AFPC-0016 [CMOCA-FM-076]|
| BCOCA™ Frequently Asked Questions | AFPC-0011 [CMOCA-FM-077]|
| Metadata Guide for AFP | AFPC-0018 [CMOCA-FM-078]|
| MO:DCA-L: The OS/2 PM Metafile (.met) Format | AFPC-0014 [CMOCA-FM-079]|
| Presentation Object Subsets for AFP | AFPC-0002 [CMOCA-FM-080]|
| Recommended IPDS™ Values for Object Container Versions | AFPC-0017 [CMOCA-FM-081]|

### Table 3. AFP Font-Related Documentation

| Publication | Book Identification [CMOCA-FM-082]|
| :--- | :--- |
| Character Data Representation Architecture Reference and Registry | SC09-2190 (IBM) [CMOCA-FM-083]|
| Font Summary for AFP Font Collection | S544-5633 (IBM) [CMOCA-FM-084]|
| Technical Reference for Code Pages | S544-3802 (IBM) [CMOCA-FM-085]|

## Changes in This Edition

Changes between this edition and the previous edition are marked by a vertical bar “|” in the left margin. [CMOCA-FM-086]

This edition provides enhanced detail to support the CMOCA products that were introduced in the years 2012 through 2024 and to support the work of the AFP Consortium. Specifically, the following new function and clarification has been provided: [CMOCA-FM-087]

*   Known, device-independent, process colorant names in the Colorant Identification List tag [CMOCA-FM-088]
*   Clarifications for: [CMOCA-FM-089]
    *   How color intensities vary as the intensity values move from 0 to the maximum value [CMOCA-FM-090]
    *   Color Palette Named Colorants tag description [CMOCA-FM-091]

## Table of Contents

*   [Preface](#preface) [CMOCA-FM-092]
*   [Publication History](#publication-history) [CMOCA-FM-093]
*   [How to Use This Book](#how-to-use-this-book) [CMOCA-FM-094]
*   [Interpreting the Syntax](#interpreting-the-syntax) [CMOCA-FM-095]
*   [Related Publications](#related-publications) [CMOCA-FM-096]
*   [Changes in This Edition](#changes-in-this-edition) [CMOCA-FM-097]
*   [Chapter 1. A Presentation Architecture Perspective](Chapter_1.md) [CMOCA-FM-098]
*   [Chapter 2. Introduction to CMOCA](Chapter_2.md) [CMOCA-FM-099]
*   [Chapter 3. Color Management Resource (CMR)](Chapter_3.md) [CMOCA-FM-100]
*   [Chapter 4. CMR Types](Chapter_4.md) [CMOCA-FM-101]
*   [Chapter 5. CMR Data Architecture](Chapter_5.md) [CMOCA-FM-102]
*   [Chapter 6. CMR Processing](Chapter_6.md) [CMOCA-FM-103]
*   [Appendix A. Tag Registry](Appendix_A.md) [CMOCA-FM-104]
*   [Appendix B. Generic CMR Name Registry](Appendix_B.md) [CMOCA-FM-105]
*   [Appendix C. Compliance With Color Management Object Content Architecture](Appendix_C.md) [CMOCA-FM-106]
*   [Glossary](Appendix_C.md#glossary) [CMOCA-FM-107]
*   [Index](Appendix_C.md#index) [CMOCA-FM-108]
