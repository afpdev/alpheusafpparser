# Coverage Concept: Alpheus AFP Parser

This document defines the high-level strategy and technical methodology for increasing the Alpheus AFP Parser's code coverage from ~15% to the target of **85%**.

---

## 1. Vision & Objectives

The primary goal is to ensure that every MO:DCA structured field, triplet, and control sequence is not just "parsed" at the surface level, but deeply decoded and verified for data integrity.

### Key Metrics
- **Primary Target:** 85% Instruction Coverage.
- **Secondary Target:** 85% Branch Coverage.
- **Verification:** 100% successful round-trip for all implemented AFP elements.

---

## 2. Tiered Implementation Strategy

Based on the `COVERAGE_GAP.md` analysis, efforts are prioritized into tiers to maximize impact on the overall architecture.

### Tier 1: Core Building Blocks (MO:DCA & Triplets)
- **Focus:** `com.mgz.afp.modca`, `com.mgz.afp.triplets`.
- **Reasoning:** These form the skeleton of every AFP document.
- **Goal:** Reach 80%+ coverage by implementing systematic round-trip tests for all 140+ individual classes in these packages.

### Tier 2: Data-Heavy Objects (GOCA, IOCA, FOCA)
- **Focus:** `com.mgz.afp.goca`, `com.mgz.afp.ioca`, `com.mgz.afp.foca`.
- **Reasoning:** These contain complex drawing orders and bitstream parsing logic which are currently almost entirely untested.
- **Goal:** Exercise every drawing order (GOCA) and image segment (IOCA) using synthetic binary generation.

### Tier 3: High-Throughput Components (Performance Fast-Paths)
- **Focus:** `com.mgz.xml.AfpJacksonXmlWriter`, `com.mgz.util.PTXPerformanceMonitor`.
- **Reasoning:** `AfpJacksonXmlWriter` is the single most uncovered class by volume. It contains specialized fast-paths for high-frequency elements like `PTX` and `NOP`.
- **Goal:** Create targeted unit tests that compare the output of specialized fast-paths against the generic reflective paths to ensure 100% equivalence.

---

## 3. Methodology: Deep Round-Trip Verification

The "Shallow Parsing" identified in `COVERAGE_GAP.md` occurs when a test merely verifies that a `StructuredField` object is created. Our strategy to overcome this is **Deep Round-Trip Verification**.

### The Round-Trip Pattern
For every component (Structured Field, Triplet, or Order):
1.  **Programmatic Generation:** Manually construct a `byte[]` payload that exercises all optional parameters and edge-case values.
2.  **Decoding:** Parse the payload into a high-level Java object.
3.  **Validation:** Assert that the object properties match the intended values (referencing specification IDs).
4.  **Encoding:** Serialize the object back to a `byte[]`.
5.  **Equality Check:** Verify that the original and re-encoded byte arrays are identical.

This approach ensures that both the `decode()` and `write()` logic paths are fully exercised.

---

## 4. Requirement-Driven Coverage

Coverage is not just about lines of code, but about functional compliance.

- **Traceability:** Every new test must explicitly reference a Requirement ID from the `/specifications/markdown/` directory (e.g., `[MODCA-3-021]`).
- **Gap Verification:** The `TEST_COVERAGE_*.md` files act as the "Source of Truth" for what remains to be verified. A requirement is only marked as "Covered" when a corresponding Deep Round-Trip test exists.

---

## 5. Robustness & Edge Cases

To bridge the gap between "happy path" coverage and real-world robustness:

### A. Synthetic Stream Stressing
Generate streams with:
- **SFI Extensions:** Exercise padding, segmentation, and encryption flags in the `StructuredFieldIntroducer`.
- **Length Variations:** Test minimum, maximum, and invalid lengths for all variable-sized fields.

### B. Integrated Fuzzing
Leverage `AFPFuzzTest` (Jazzer) to:
- Proactively discover unhandled `AFPParserException` branches.
- Use discovered "crasher" inputs as the basis for new unit tests.

### C. Performance Instrumentation Coverage
Ensure that monitoring logic (`MnemonicPerformanceMonitor`, `PTXPerformanceMonitor`) is exercised by running tests in both "Standard" and "Performance Debug" modes.

---

## 6. Continuous Monitoring

- **JaCoCo Integration:** Run `./gradlew jacocoTestReport` as part of every local development cycle.
- **Gap Updates:** Update `COVERAGE_GAP.md` and `COVERAGE_ROADMAP.md` in lockstep with significant PRs to visualize progress toward the 85% goal.
