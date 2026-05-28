# Concept: ADF to PDF/VT Conversion

This document outlines the conceptual architecture and implementation plan for converting the Alpheus AFP object model (ADF) to **PDF/VT (ISO 16612-2)**. PDF/VT is specifically designed for Variable Data and Transactional printing, making it the ideal target for AFP streams.

## Architecture Overview

The conversion process follows a three-stage pipeline, optimized for PDF/VT requirements:

1.  **Object Model Traversal**: Recursive traversal of the Alpheus object tree, mapping MO:DCA document structure to the **PDF/VT Document Part (DPart) Hierarchy**.
2.  **Resource Resolution**: Dynamic lookup and mapping of AFP resources (Fonts, Images, Form Maps) to **Global Resources** in the PDF/VT structure.
3.  **Primitive Mapping**: Translation of AFP content architectures (PTOCA, GOCA, IOCA, BCOCA) into PDF operators, utilizing PDF/X-4/5 base standards.

---

## PDF/VT Specific Mappings (ISO 16612-2)

### 1. Document Structure (MO:DCA to DPart Hierarchy)
- **AFP**: MO:DCA `BNG`/`ENG` (Named Page Groups) and `BDT`/`EDT` (Documents).
- **PDF/VT (ISO 16612-2, §10)**:
    - **DPartRoot**: The root of the Document Part Hierarchy.
    - **DPart Hierarchy**: Map MO:DCA nested Page Groups to a multi-level `/DPart` tree.
    - **DPart Metadata**:
        - Translate `TLE` (Tag Logical Element) triplets into entries in the `/DPart` dictionary's metadata or `/Property` entries.
        - This enables high-speed indexing and record-level processing in production RIPs.

### 2. Variable Data Optimization (Overlays to Form XObjects)
- **AFP**: `BMO`/`EMO` (Overlays) and `BPS`/`EPS` (Page Segments).
- **PDF/VT Strategy**:
    - Overlays are mapped to **Form XObjects** (ISO 32000-1, §8.10).
    - To comply with PDF/VT performance goals, these XObjects are stored in the **Global Resources** of the PDF/VT file.
    - Repeated use of an overlay across thousands of pages uses a single `Do` operator reference, minimizing file size and RIP time.

### 3. Coordinate Transformation & Graphics State
- **Transformation**: Apply CTM (`cm` operator) to map AFP coordinate systems to PDF User Space (§8.3), respecting PDF/VT's requirement for PDF/X-4/5 compliance (e.g., proper `/MediaBox` and `/TrimBox` definitions).
- **Transparency**: Utilize PDF transparency groups (§11) where AFP uses mixing rules (e.g., Overstrike, GOCA Mix), as permitted by PDF/X-4 (the base for PDF/VT-1).

### 4. Content Architecture Mapping
- **Text (PTOCA)**: Map to PDF Text Objects (§9) using embedded fonts. Utilize PDF/VT's character-level positioning for precise PTOCA `AMI`/`RMI` reproduction.
- **Graphics (GOCA)**: Map to PDF path construction operators (§8.5).
- **Images (IOCA)**: Wrap in Image XObjects (§8.9).

---

## Detailed Implementation Task List

### Phase 1: PDF/VT Structural Implementation
- [ ] Initialize **DPart Hierarchy** (`/DPartRoot`) in the PDF Catalog.
- [ ] Implement MO:DCA `BNG` to PDF `/DPart` recursive mapping.
- [ ] Map `TLE` values to Record-level metadata.
- [ ] Define `/OutputIntents` (PDF/X compliance) as required by ISO 16612-2.

### Phase 2: Resource Management & Optimization
- [ ] **Global Resource Manager**: Implement logic to move shared XObjects (Overlays, Page Segments) to the global Page Tree resources.
- [ ] **FOCA to PDF/X-4 Font Embedding**: Ensure all fonts are fully embedded and subsetted as per PDF/X-4 requirements.
- [ ] **IOCA Image Optimizer**: Map repeated IOCA objects to a single Image XObject instance.

### Phase 3: Content Conversion (Base Operators)
- [ ] **PTOCA Driver**: Map control sequences to `BT`/`ET` and `Td`/`Tm` operators.
- [ ] **GOCA Driver**: Map path drawing orders (Line, Arc, Area) to PDF paths.
- [ ] **BCOCA Renderer**: Draw barcodes using vector primitives to ensure resolution independence.

### Phase 4: Verification & Compliance
- [ ] Validate generated files against **PDF/VT-1** profiles using preflight tools (e.g., Callas pdfToolbox or Adobe Acrobat Pro).
- [ ] Verify DPart hierarchy navigation in PDF/VT-aware viewers.
- [ ] Compare record-level extraction from PDF metadata against original AFP `TLE` values.
