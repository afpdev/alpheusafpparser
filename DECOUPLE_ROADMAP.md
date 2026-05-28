# Decoupling AFP Parser from XML Generation - Roadmap

This roadmap outlines the serial implementation plan for decoupling the parser from XML generation.

## Phase 1: Abstraction Layer (Core Interface)
**Goal:** Define the contracts for handlers and factories.

1.  **Define `StructuredFieldHandler`:** Create the interface in `com.mgz.afp.base.handler`.
2.  **Define `HandlerFactory`:** Create the interface for handler instantiation.
3.  **Create `XmlHandlerFactory`:** A concrete implementation that encapsulates the logic for choosing between Streaming and Jackson XML writers.

## Phase 2: Refactoring Output Modules (XML)
**Goal:** Adapt existing XML writers to the new abstraction.

1.  **Update `AfpStreamingXmlWriter`:** Implement `StructuredFieldHandler`.
2.  **Update `AfpJacksonXmlWriter`:** Implement `StructuredFieldHandler`.
3.  **Unify XML Writing Logic:** Ensure both writers use the same method signature for field processing.

## Phase 3: Integration (Orchestrators & CLI)
**Goal:** Update the parser's consumers to use the abstraction.

1.  **Refactor `ParallelAfpConverter`:**
    - Replace `useJackson` and `xpathExpression` flags with a `HandlerFactory` instance.
    - Update `PageConversionTask` to use the factory.
2.  **Refactor `Afp2Xml` CLI:**
    - Update the `convertToXml` method to instantiate an `XmlHandlerFactory`.
    - Adapt the sequential parsing loop to use the `StructuredFieldHandler` interface.

## Phase 4: Performance Validation & Regression
**Goal:** Ensure zero performance loss and bit-for-bit XML equality.

1.  **Equality Check:** Run the `Afp2Xml` CLI on a set of reference AFP files and compare the output against previous versions to ensure no regressions in XML format.
2.  **Performance Benchmarking:** Run `PerformanceRegressionTest` and manual benchmarks using the `-m` (measure) flag. Compare results with the "Re-measured" baselines in `PERFORMANCE_CONCEPT.md`.
3.  **Concurrency Testing:** Verify that parallel conversion remains thread-safe and efficient with the new handler-per-thread model.

## Phase 5: PDF Generator Foundation
**Goal:** Prepare for high-performance PDF generation.

1.  **Stub `PdfHandler`:** Create a skeleton implementation for performance testing (parsing without output).
2.  **Benchmark PDF vs XML:** Measure the throughput of the parsing stage alone to establish a ceiling for PDF generation speed.
