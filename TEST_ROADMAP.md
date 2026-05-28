# Alpheus AFP Parser Testing Roadmap

This roadmap outlines the evolution of testing for the Alpheus AFP Parser, from infrastructure modernization to advanced fuzzing and semantic validation.

## Progress Overview

| Phase | Description | Status |
| :--- | :--- | :---: |
| 1 | Infrastructure & JUnit 5 Migration | ✅ |
| 2 | Implementation Depth & Round-Trip | ✅ |
| 3 | Error Handling & Resilience | ✅ |
| 4 | Fuzzing and Malicious Data | 🏗️ |
| 5 | Semantic Validation | ⏳ |

## Goals
- ✅ Achieve 100% architectural coverage via 1:1 spec-to-test mapping.
- ✅ Ensure 0 "shallow" fields through comprehensive round-trip tests.
- 🚧 Integrate systematic fuzzing for complex Structured Field structures.
- ⏳ Expand semantic validation for architectural constraints.

---

## Phase 1: Infrastructure & JUnit 5 Migration
- ✅ Migrate the entire test suite to JUnit 5 (Jupiter).
- ✅ Remove legacy JUnit 4 dependencies and imports.
- ✅ Establish a 1:1 mapping between specification chapters and test files.
- ✅ Generate over 122 minimal `.afp` files to exercise every chapter of every specification.

## Phase 2: Implementation Depth & Round-Trip
- ✅ Eliminate all "shallow" fields; every Structured Field class now implements `decodeAFP`.
- ✅ Verify GOCA drawing orders via `GOCARoundTripTest.java`.
- ✅ Verify IOCA segments via `IOCARoundTripTest.java`.
- ✅ Verify FOCA and BCOCA Structured Fields via dedicated round-trip tests.
- ✅ Verify stateful encoding and character set switching (e.g., `SCFL` in PTX).

## Phase 3: Error Handling & Resilience
- ✅ Implement `ErrorHandlingTest.java` to verify resilience against malformed data.
- ✅ Handle missing `0x5A` markers and premature EOF.
- ✅ Handle truncated Structured Field Introducers (SFI) and missing extensions.
- ✅ Handle inconsistent lengths (SFI length vs. actual payload data).
- ✅ Handle malformed Triplet sequences (invalid lengths, oversized triplets).

## Phase 4: Fuzzing and Malicious Data
- 🚧 **Systematic Fuzzing:** Integrate a fuzzing tool (e.g., Jazzer or a custom mutator) to explore deeper edge cases.
- ⏳ **Semi-Valid Data Exploration:** Generate AFP streams with valid headers but mutated/randomized payloads to test parser robustness.
- ⏳ **Complex SF Structure Testing:** Specifically target complex nested structures in MO:DCA and GOCA for boundary condition testing.

## Phase 5: Semantic Validation
- ⏳ **Architectural Constraints:** Expand tests to validate mandatory relationships between fields and triplets (e.g., mandatory triplets).
- ⏳ **Semantic Rules:** Implement validation logic for cross-field dependencies defined in the AFP specifications.
- ⏳ **Advanced Validations:** Verify that the extracted human-readable text and XML output conform to architectural expectations.
