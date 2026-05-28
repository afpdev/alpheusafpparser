# Alpheus AFP EBNF Roadmap

This roadmap outlines the evolution of the formal EBNF (Extended Backus-Naur Form) descriptions for the AFP data stream, providing a machine-readable syntax for all structured fields, triplets, and drawing orders.

## Progress Overview

| Phase | Description | Status |
| :--- | :--- | :---: |
| 1 | Base Conventions & Primitive Types | ✅ |
| 2 | MO:DCA Structured Field Introducer (SFI) | ✅ |
| 3 | MO:DCA Document Structure | ✅ |
| 4 | Structured Fields (SF) Definitions | ⏳ |
| 5 | Triplets Definitions | ✅ |
| 6 | PTOCA Control Sequences | ⏳ |
| 7 | GOCA Drawing Orders | ⏳ |
| 8 | IOCA Function Sets & Segments | ⏳ |
| 9 | BCOCA & FOCA Definitions | ⏳ |
| 10 | IPDS Command Syntax | ⏳ |
| 11 | Line Data Syntax | ⏳ |

## Goals
- ⏳ Establish a unified EBNF syntax for the entire AFP ecosystem.
- ⏳ Enable automated validation of AFP data streams against formal grammars.
- ⏳ Provide clear, unambiguous documentation for implementers.

---

## Phase 1: Base Conventions & Primitive Types
*Definition of the fundamental building blocks used across all AFP specifications.*

- ✅ **Core Primitives**:
    - ✅ HEX: 8-bit byte representation.
    - ✅ BIT: Individual bit definitions.
    - ✅ UBIN: Unsigned binary integers (1B, 2B, 3B, 4B).
    - ✅ SBIN: Signed binary integers.
    - ✅ CODE: Architectural constants and IDs.
    - ✅ CHAR: EBCDIC/ASCII character strings.
- ✅ **Files**:
    - [primitives.ebnf](primitives.ebnf)
    - [primitives.puml](primitives.puml)

## Phase 2: MO:DCA Structured Field Introducer (SFI)
*Formalizing the 8-byte (or more with extensions) header for every MO:DCA structured field.*

- ✅ **SFI Syntax**:
    - ✅ SFLength: 2-byte length field.
    - ✅ SFTypeID: 3-byte identifier (D3TTCC).
    - ✅ FlagByte: Extension, Segmentation, and Padding flags.
    - ✅ SFI Extension: Optional extension data.
- ✅ **Files**:
    - [sfi.ebnf](sfi.ebnf)
    - [sfi.puml](sfi.puml)

## Phase 3: MO:DCA Document Structure
*Defining the hierarchical relationship between Begin/End pairs and nested objects.*

- ✅ **Document Hierarchy**:
    - ✅ PrintFile: Root container.
    - ✅ Resource Groups.
    - ✅ Document, PageGroup, and Page structures.
    - ✅ Active Environment Group (AEG).
- ✅ **Files**:
    - [structure.ebnf](structure.ebnf)
    - [structure.puml](structure.puml)

## Phase 4: Structured Fields (SF) Definitions
*Detailed EBNF for every Structured Field payload.*

- ⏳ **SF Categories**:
    - ⏳ Presentation Commands (e.g., IPS, PGD).
    - ⏳ Layout Objects (e.g., BDI/EDI, BPG/EPG).
    - ⏳ Resource Objects (e.g., BRS/ERS).

## Phase 5: Triplets Definitions
*Syntax for self-identifying parameters that can be appended to many structured fields.*

- ✅ **Common Triplets**:
    - ✅ Fully Qualified Name (X'02').
    - ✅ Mapping Option (X'04').
    - ✅ Object Classification (X'10').
- ✅ **Files**:
    - [triplets.ebnf](triplets.ebnf)
    - [triplets.puml](triplets.puml)

## Phase 6: PTOCA Control Sequences
*Formalizing the Presentation Text Data (PTD) internal syntax.*

- ⏳ **Control Sequences**:
    - ⏳ Absolute/Relative Move (AMB, RMB, AMI, RMI).
    - ⏳ Text Control (SEC, SCFL, TERN).

## Phase 7: GOCA Drawing Orders
*Syntax for Graphics Data (GAD) drawing primitives.*

- ⏳ **Drawing Orders**:
    - ⏳ Line, Box, Circle, and Arc primitives.
    - ⏳ Attribute orders (Set Color, Set Mix).

## Phase 8: IOCA Function Sets & Segments
*Image Object Content Architecture definitions.*

- ⏳ **IOCA Syntax**:
    - ⏳ Image Content Program (ICP).
    - ⏳ Image Data Descriptor (IDD).

## Phase 9: BCOCA & FOCA Definitions
*Bar Code and Font Object Content Architecture.*

- ⏳ **BCOCA**: Bar Code Data Descriptor (BDD) and Bar Code Data (BDA).
- ⏳ **FOCA**: Font descriptors, character metrics, and patterns.

## Phase 10: IPDS Command Syntax
*Bidirectional printer command stream syntax.*

- ⏳ **IPDS Commands**:
    - ⏳ Device Control, Load Font, and IO Image commands.

## Phase 11: Line Data Syntax
*Legacy line data and its integration with AFP.*

- ⏳ **Line Data**:
    - ⏳ Carriage Control Characters (ANSI/Machine).
    - ⏳ Table Reference Characters (TRC).
