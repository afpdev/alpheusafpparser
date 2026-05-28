# Chapter 6. Compliance

This chapter describes the MOCA subsets that are supported in the MOCA architecture. [MOCA-6-001]

## MOCA Subsets

MOCA subsets are used to identify a specific level of MOCA functionality. Each new (higher level) subset must incorporate the complete functionality of the previous (lower level) subset. The naming of MOCA subsets is defined as follows. [MOCA-6-002]

Each new MOCA subset will be identified by the term **MSx**, where:
* **MS** = Metadata Subset [MOCA-6-003]
* **x** = level, starting with 1 [MOCA-6-004]

## MS1 Subset

The MS1 Subset is the level of MOCA compliance required to support the functionality contained in the first edition of the MOCA Reference. [MOCA-6-005]

The following value of **MOType** must be supported:
* DES

The following value of **MOFormat** must be supported:
* XMP

The following values of **MOCompression** must be supported:
* NONE
* GZIP
* EXI

In environments where exception conditions can be reported, the following exception conditions must be supported:
* EC-0100 [MOCA-6-006]
* EC-0200 [MOCA-6-007]
* EC-0210 [MOCA-6-008]
* EC-0220 [MOCA-6-009]
* EC-0230 [MOCA-6-010]
* EC-0240 [MOCA-6-011]
* EC-0250 [MOCA-6-012]
* EC-0300 [MOCA-6-013]

---

# Notices
The AFP Consortium or consortium member companies might have patents or pending patent applications covering subject matter described in this document. The furnishing of this document does not give you any license to these patents. [MOCA-6-014]

The following statement does not apply to the United Kingdom or any other country where such provisions are inconsistent with local law: **AFP Consortium PROVIDES THIS PUBLICATION “AS IS” WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.** Some states do not allow disclaimer of express or implied warranties in certain transactions, therefore, this statement might not apply to you. [MOCA-6-015]

This publication could include technical inaccuracies or typographical errors. Changes are periodically made to the information herein; these changes will be incorporated in new editions of the publication. The AFP Consortium might make improvements and/or changes in the architecture described in this publication at any time without notice. [MOCA-6-016]

Any references in this publication to Web sites are provided for convenience only and do not in any manner serve as an endorsement of those Web sites. The materials at those Web sites are not part of the materials for this architecture and use of those Web sites is at your own risk. [MOCA-6-017]

The AFP Consortium may use or distribute any information you supply in any way it believes appropriate without incurring any obligation to you. [MOCA-6-018]

This information contains examples of data and reports used in daily business operations. To illustrate them in a complete manner, some examples include the names of individuals, companies, brands, or products. These names are fictitious and any similarity to the names and addresses used by an actual business enterprise is entirely coincidental. [MOCA-6-019]

# Trademarks
PostScript and XMP are either registered trademarks or trademarks of Adobe Systems Incorporated in the United States and/or other countries. [MOCA-6-020]

AFPC and AFP Consortium are trademarks of the AFP Consortium. [MOCA-6-021]

IBM is a trademark of the International Business Machines Corporation in the United States, other countries, or both. [MOCA-6-022]

These terms are trademarks or registered trademarks of Ricoh Co., Ltd., in the United States, other countries, or both:
* ACMA
* Advanced Function Presentation [MOCA-6-023]
* AFP
* AFPCC
* AFP Color Consortium [MOCA-6-024]
* AFP Color Management Architecture [MOCA-6-025]
* Bar Code Object Content Architecture [MOCA-6-026]
* BCOCA
* CMOCA
* Color Management Object Content Architecture [MOCA-6-027]
* InfoPrint [MOCA-6-028]
* Intelligent Printer Data Stream [MOCA-6-029]
* IPDS
* Mixed Object Document Content Architecture [MOCA-6-030]
* MO:DCA [MOCA-6-031]
* Ricoh

Other company, product, or service names might be trademarks or service marks of others. [MOCA-6-032]
