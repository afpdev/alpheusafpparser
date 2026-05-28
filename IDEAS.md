# Future Improvement Ideas for Alpheus AFP Parser

This document outlines potential areas for future enhancement, categorized to guide the project's evolution in performance, compliance, and developer experience.

## Performance & Scalability
1.  **Typed Access API**: Implement StAX2 `writeInteger()`, `writeLong()`, and `writeBinary()` in manual fast-paths to eliminate temporary `String` allocations during XML generation.
2.  **Non-blocking (Async) Parsing**: Develop an asynchronous `AFPParser` using Aalto XML's async capabilities for high-throughput reactive environments like Vert.x or Netty.
3.  **SIMD Optimization**: Utilize the Java Vector API for bulk character detection and encoding analysis in `isHumanReadable` and EBCDIC decoding.
4.  **Off-heap Memory**: Introduce `DirectByteBuffer` or the Java Foreign Memory API for large structured field payloads to minimize GC impact in high-volume processing.
5.  **Source Generation**: Use an annotation processor to automatically generate StAX fast-paths and object-pooling logic for all 200+ Structured Fields.
6.  **Zero-Copy Base64**: Leverage StAX2's native `writeBinary()` for fields like `GAD` (Graphics Data) and `OCD` (Object Container Data) to avoid large temporary byte arrays.
7.  **Alternative StAX Providers**: Benchmark Woodstox against Aalto for scenarios where XML schema validation (XSD/DTD) is required during the streaming phase.

## Architecture & Design
8.  **Java Modules (JPMS)**: Transition to a modular architecture to allow consumers to include only necessary sub-architectures (e.g., MO:DCA/PTOCA) and improve encapsulation.
9.  **Multi-module Gradle**: Strictly separate the core parser, XML streaming engines, and future conversion drivers (e.g., PDF/VT) into distinct Gradle modules.
10. **Visitor Pattern**: Implement a formal Visitor across the `AFPDocument` tree to simplify the creation of custom third-party analyzers, indexers, or transformers.
11. **Tunable Pooling**: Expose Object Pooling configuration (SFI, Triplets, SFs) via `AFPParserConfiguration` to allow fine-grained memory tuning for different workloads.

## Specification Compliance
12. **MODCA Granular Analysis**: Automate the extraction of granular requirements from MO:DCA specifications into the test suite to improve the current 0% coverage metric.
13. **IPDS Support**: Prioritize full support for the Intelligent Printer Data Stream (IPDS), handling its complex bi-directional state management and commands.
14. **Extensible Registry**: Provide a runtime registry for users to inject custom decoders and writers for "Unknown" or proprietary Structured Fields.
15. **Line Data (XMD)**: Implement XML Descriptor (XMD) support to facilitate the conversion and management of legacy Line Data formats.

## Output Formats & Conversion
16. **PDF/A-3 Archival**: Target PDF/A-3 compliance to allow embedding the original AFP file as an attachment within the generated PDF for archival integrity.
17. **Font Substitution**: Implement a mapping engine to substitute missing FOCA fonts with system TTF/OTF fonts during PDF conversion using font metrics.
18. **Streaming PDF Writer**: Develop a PDF driver that generates pages incrementally as they are parsed, maintaining an O(1) memory footprint for multi-GB files.
19. **Modern Serialization**: Add support for JSON or Protobuf output formats to support modern web-based data pipelines and mobile applications.
20. **XSD Generation**: Include automatic XML Schema (XSD) generation for exported data to enable formal downstream validation.

## Testing & Quality Assurance
21. **Systematic Fuzzing**: Integrate Jazzer (libFuzzer for the JVM) into the CI pipeline to proactively discover edge-case crashes and buffer overflows in the parser.
22. **Property-Based Testing**: Use jqwik to verify that Parse -> Write round-trips always produce bit-identical or semantically equivalent results for any valid input.
23. **Visual Regression**: Automate layout fidelity verification by comparing PDF output against reference AFP renderers (e.g., IBM AFP Workbench).
24. **Performance Gate**: Add a CI gate that fails builds if parsing throughput or memory efficiency drops below a defined baseline.

## Developer Experience & Ecosystem
25. **CLI Lint Mode**: Provide a "Lint" mode to check for violations of AFP interchange sets (e.g., MODCA IS/3) and report them with actionable error messages.
26. **Cloud-Native Starters**: Develop Spring Boot Starters or Quarkus Extensions to simplify integration for developers building microservices.
27. **Enhanced CLI UX**: Improve progress reporting for multi-gigabyte files using terminal-based progress bars (e.g., jline).
28. **Wasm Inspector**: Compile the core parser to WebAssembly via GraalVM to create a zero-install browser-based AFP structure inspector.

## Security & Robustness
29. **AFP Bomb Protection**: Implement policy checks to prevent malicious recursive or highly compressed structures from exhausting CPU or memory.
30. **PII Redaction**: Add a native redaction mode to the XML and PDF output engines to mask sensitive data (e.g., account numbers) during the conversion process.
