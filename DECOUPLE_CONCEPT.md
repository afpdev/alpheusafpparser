# Decoupling AFP Parser from XML Generation

## Goal
The primary goal is to decouple the Alpheus AFP Parser from its current hardcoded XML generation logic. This will enable the parser to be reused for other high-performance output formats, specifically PDF generation, without sacrificing the existing performance gains achieved through streaming and parallelization.

## Business & Use Case
- **Multi-Format Support:** Modern document processing pipelines often require converting legacy AFP (Advanced Function Presentation) streams into various formats. While XML is excellent for data extraction and indexing, PDF is the industry standard for viewing and distribution.
- **High-Performance PDF Generation:** By leveraging the existing parallel scanning and page-based parsing infrastructure of Alpheus, we can achieve significantly higher throughput for PDF generation compared to traditional sequential converters.
- **Platform Extensibility:** Decoupling allows third-party developers to implement custom handlers (e.g., for JSON, HTML5, or direct database ingestion) without modifying the core parser.

## High-Level Architecture
The architecture shifts from a "Push-to-XML" model to a generic **Handler-based Event Model**.

### Current Coupled Architecture
Currently, the `AFPParser` or the `ParallelAfpConverter` directly instantiates `AfpStreamingXmlWriter` or `AfpJacksonXmlWriter` and calls their `writeField(sf)` method. This hardcodes the dependency on XML-specific logic and Jackson/JAXB libraries.

### Proposed Decoupled Architecture
We introduce a `StructuredFieldHandler` abstraction. The parser or orchestrator (like `ParallelAfpConverter`) becomes agnostic of the output format.

1.  **`StructuredFieldHandler` Interface:** Defines a standard contract for receiving structured fields.
2.  **`Handler Implementations`:**
    - `XmlHandler`: Wraps existing XML writing logic.
    - `PdfHandler`: New implementation for PDF generation.
3.  **`Handler Factory`:** Provides a mechanism to create thread-local handler instances, which is critical for maintaining performance in multi-core parallel processing.
4.  **`Orchestrator`:** The `ParallelAfpConverter` is refactored to work with a `HandlerFactory` instead of specific XML writers.

### Performance Preservation Strategy
To ensure "zero performance loss," the decoupling follows these principles:
- **Zero-Copy Passing:** The `StructuredField` objects are passed by reference to the handler.
- **No Intermediate Representations:** No conversion to intermediate maps or generic objects occurs before reaching the handler.
- **Thread Locality:** Each parallel worker thread uses its own handler instance to avoid synchronization overhead.
- **Lifecycle Management:** The handler or orchestrator continues to manage the `release()` cycle of structured fields to maintain the efficiency of the object pools.
