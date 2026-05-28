# Alpheus AFP Parser Code Coverage Roadmap

This document outlines a phased, actionable plan to increase the code coverage of the Alpheus AFP Parser from its current ~15% to the target of **85%**.

## Status Summary (May 2026)
- **Current Coverage:** 15.09% (Instruction)
- **Target Coverage:** 85.00%
- **Gap:** 69.91%

---

## Phase 1: Foundation - MO:DCA & Triplets (Target: 35%) 🚧
The goal of this phase is to cover the most frequent building blocks of AFP documents.

### 1.1. Systematic Round-Trip Testing for MO:DCA 🚧
- ⏳ Implement `RoundTripTest` classes for all 80+ Structured Fields in `com.mgz.afp.modca`.
- ⏳ Use `RoundTripTestUtils` to verify that every SF can be decoded and encoded without data loss.
- **Methodology: Deep Round-Trip Verification**
    1. **Programmatic Generation:** Manually construct a `byte[]` payload that exercises all optional parameters and edge-case values.
    2. **Decoding:** Parse the payload into a high-level Java object.
    3. **Validation:** Assert that the object properties match the intended values (referencing specification IDs).
    4. **Encoding:** Serialize the object back to a `byte[]`.
    5. **Equality Check:** Verify that the original and re-encoded byte arrays are identical.
- ✅ **Priority:** `BDT`, `EDT`, `BPG`, `EPG`, `BAG`, `EAG` (completed in `DocumentAndPageGroupRoundTripTest.java` and `EnvironmentAndResourceGroupRoundTripTest.java`).

### 1.2. Triplet Coverage Expansion ✅
- ✅ Implement round-trip unit tests for all 60+ Triplets in `com.mgz.afp.triplets` (completed in `TripletRoundTripTest.java`).
- ✅ Exercise all optional parameters and varying lengths for complex triplets like `FullyQualifiedName` (X'02') and `ColorSpecification` (X'4E').

---

## Phase 2: Architectural Depth - GOCA, IOCA, FOCA (Target: 55%) 🚧
This phase focuses on the data-heavy sub-specifications.

### 2.1. GOCA Drawing Orders ✅
- ✅ Create a new suite for `com.mgz.afp.goca`: `GOCADrawingOrderRoundTripTest`.
- ✅ Test Drawing Orders via round-trip logic.
    - ✅ **Basic & Attribute Orders:** `GNOP1`, `GCOMT`, `GSCOL`, `GSMX`, `GSBMX`, `GSLT`, `GSLW`, `GSLE`, `GSLJ`, `GSPS`, `GSCS`, `GSCD`, `GSCR`, `GSMP`, `GSECOL`, `GSPT`, `GSMT`, `GSMS`, `GSFLW`.
    - ✅ **Positioning & Control:** `GSCP`, `GSAP`, `GSCC`, `GSCA`, `GSCH`, `GSMC`, `GSPRP`, `GEPROL`, `GSPIK`, `GBSEG`, `GESEG`, `GSGCH`.
    - ✅ **Geometric Primitives:** `GLINE`, `GRLINE`, `GCLINE`, `GCRLINE`, `GMRK`, `GCMRK`, `GBOX`, `GCBOX`.
    - ✅ **Areas & Images:** `GBAR`, `GEAR`, `GCCHST`, `GCHST`.
    - ✅ **Miscellaneous:** `GEXO`.
    - ✅ **Advanced Primitives:** `GARC`, `GPARC`, `GCPARC`, `GFARC`, `GCFARC`, `GFLT`, `GCFLT`, `GCCBEZ`, `GCBEZ`.
    - ✅ **Complex Control:** `GBCP`, `GECP`, `GDPT`, `GSPCOL`, `GLGD`, `GRGD`, `GIMD`, `GEIMG`, `GBIMG`, `GCBIMG`.

### 2.2. IOCA Image Segments ✅
- ✅ Exercise image segments in `com.mgz.afp.ioca`.
    - ✅ **Structure & Identity:** `BeginSegment`, `EndSegment`, `BeginImageContent`, `EndImageContent`, `FunctionSetIdentification`.
    - ✅ **Parameters:** `ImageSize`, `ImageEncoding`, `IDESize`, `IDEStructure`, `ImageLUTID`.
    - ✅ **Advanced Features:** `BandImage`, `ExternalAlgorithmSpecification`, `ImageSubsampling`, `BeginTile`, `EndTile`, `TilePosition`, `TileSize`, `TileSetColor`, `IncludeTile`, `TileTOC`, `BeginTransparencyMask`, `EndTransparencyMask`, `SetExtendedBilevelImageColor`, `SetBilevelImageColor`.
    - ✅ **Data:** `ImageData`, `BandImageData`, `nColorNames`.
- ⏳ Verify support for different compression types (MMR, JPEG, etc.) at the parsing level.

### 2.3. FOCA Font Logic ⏳
- ⏳ Implement tests for Font Character Sets and Code Pages in `com.mgz.afp.foca`.

---

## Phase 3: Robustness & Edge Cases (Target: 75%) 🚧
Focus on branch coverage and defensive logic.

### 3.1. Systematic Fuzzing ⏳
- ⏳ Integrate `AFPFuzzTest` (Jazzer) into the regular CI feedback loop.
- ⏳ Use the discovered "crashers" and "new paths" to create regression tests for complex decoding logic.

### 3.2. SFI Extensions & Padding ✅
- ✅ Create synthetic AFP streams that exercise Structured Field Introducer (SFI) extensions:
    - ✅ **Segmentation:** SFs split across multiple records (completed in `SFIExtensionsAndPaddingTest.java`).
    - ✅ **Padding:** SFs with trailing bytes (completed in `SFIExtensionsAndPaddingTest.java`).
    - ⏳ **Encryption:** SFs with the encryption flag set.
    - ✅ **Extension:** SFs with SFI extensions (completed in `SFIExtensionsAndPaddingTest.java`).
    - ⏳ **Length Variations:** Test minimum, maximum, and invalid lengths for all variable-sized fields.

### 3.3. Error Handling Paths ✅
- ✅ Explicitly test every `AFPParserException` branch in `AFPParser` and `StructuredFieldIntroducer` (completed in `SFIErrorHandlingTest.java`).

---

## Phase 4: Full Compliance & Optimization (Target: 85%+) 🚧
Cover remaining specialized areas and refactored logic.

### 4.1. Special Specifications 🚧
- ✅ Implement tests for `com.mgz.afp.moca` (completed in `MetadataObjectTest.java` and `ObjectAndDataRoundTripTest.java`).
- ⏳ Implement tests for `com.mgz.afp.lineData`, `com.mgz.afp.cmoca`, and `com.mgz.afp.bcoca`.
- ⏳ Verify the 1:1 mapping of normative requirements in `TEST_COVERAGE_*.md` files.

### 4.2. Performance fast-paths ⏳
- ⏳ Ensure that optimized fast-paths in `AfpJacksonXmlWriter` (e.g., for `PTX` and `TRN`) are verified against the generic reflective paths to ensure 100% equivalence.
- ⏳ **Performance Instrumentation Coverage:** Ensure that monitoring logic (`MnemonicPerformanceMonitor`, `PTXPerformanceMonitor`) is exercised by running tests in both "Standard" and "Performance Debug" modes.

---

## Implementation Strategy
1. **Traceability:** Every new test must explicitly reference a Requirement ID from the `/specifications/markdown/` directory (e.g., `[MODCA-3-021]`).
2. **Tooling:** Use a code generation script to create boilerplate `RoundTripTest` classes for any `StructuredField` with 0% coverage.
3. **Acceptance Integration:** Add the synthetic files generated during unit testing to the `FilesSuite` to ensure they are also exercised in end-to-end CLI tests.
4. **Continuous Monitoring:**
    - Run `./gradlew jacocoTestReport` as part of every local development cycle.
    - Update `COVERAGE_GAP.md` after every major PR to track progress against this roadmap.
