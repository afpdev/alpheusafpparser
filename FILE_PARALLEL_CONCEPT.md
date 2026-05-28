# File Parallel Concept: Single-File Multi-Core Optimization

This document explores strategies to optimize the conversion of a single large AFP file into XML by leveraging multiple CPU cores.

---

## 1. Current Weaknesses in Single-File Parallelism

### Weakness 1: Sequential XML Serialization
**Description:** While parsing can be parallelized (e.g., via `ParallelPageParser`), the XML serialization phase remains largely sequential. Currently, parsed objects are collected into a list and then written one-by-one, or threads wait for a global lock on the output stream. Since Audit 3 confirms that "Write" is the primary bottleneck, sequential writing negates the gains from parallel parsing.
- **Solution A: Parallel Write to Multiple Files**
  - Each thread writes its assigned pages to a separate temporary XML file. These files are concatenated at the end.
- **Solution B: Concurrent Ordered Buffering**
  - Threads serialize structured fields into memory buffers (e.g., `ByteArrayOutputStream`). A coordinator thread flushes these buffers to the final output stream in the correct page order.
- **Solution C: Piped Streaming**
  - Use Java `PipedOutputStream` for each worker thread, with a master thread polling them in sequence.
- **Proposed Solution:** **Solution B (Concurrent Ordered Buffering)**. This allows the expensive "Object-to-XML" serialization to happen in parallel across all cores without the I/O overhead of temporary files.

### Weakness 2: Sequential Page Boundary Scanning
**Description:** Before parallel parsing can begin, the parser must identify page boundaries (`BPG`/`EPG`). The current `AFPScanner` scans the entire file sequentially on a single thread to find these markers. For 1GB+ files, this initial scan can take several seconds, creating a "stop-the-world" initialization delay.
- **Solution A: Parallel Chunk Scanning**
  - Divide the file into N chunks (based on CPU count). Each thread finds the first `0x5A` marker in its chunk and then scans for page boundaries. Results are merged and sorted.
- **Solution B: Persistent Indexing**
  - Create a sidecar `.afp.idx` file during the first pass to store offsets, allowing near-instant boundary lookup in subsequent runs.
- **Solution C: Predictive Jump Scanning**
  - Instead of reading every byte, use the 2-byte length in the SFI to jump between records, but this is still inherently sequential in logic.
- **Proposed Solution:** **Solution A (Parallel Chunk Scanning)**. This provides a significant speedup for the initialization phase without requiring external files or changing the AFP format.

### Weakness 3: Memory Pressure from Non-Streaming Results
**Description:** `ParallelPageParser` currently returns a `List<StructuredField>` containing *all* fields in the file. For very large files, holding millions of structured field objects in heap memory causes `OutOfMemoryError` or extreme GC thrashing.
- **Solution A: Producer-Consumer Queue with Backpressure**
  - Use a `BoundedBlockingQueue` where workers put parsed/serialized pages. If the writer is slower than the parsers, the workers block, preventing memory exhaustion.
- **Solution B: Page-Level Result Flushing**
  - Threads flush their results directly to the `OrderedBuffer` (from Weakness 1) and immediately release the `StructuredField` objects for GC.
- **Solution C: Virtual Thread Offloading**
  - Use Java 21 Virtual Threads to handle thousands of small page tasks, relying on the JVM to manage scheduling and memory.
- **Proposed Solution:** **Solution A (Producer-Consumer Queue with Backpressure)**. This is the most robust way to ensure that the memory footprint remains constant (O(threads * page_size)) regardless of the input file size.

---

## 2. Summary of Proposed Architecture

| Component | Target Optimization | Proposed Technique |
| :--- | :--- | :--- |
| **Initialization** | Scan Speed | Parallel Chunk-based Boundary Scanning |
| **Parsing** | CPU Utilization | Multi-threaded Page Parsing (Existing `ParallelPageParser` base) |
| **Serialization** | Write Throughput | Parallel Object-to-XML Buffering |
| **Memory Management**| Constant Footprint | Bounded Producer-Consumer Queue |

---

## 3. Implementation Roadmap Strategy

1.  **Phase A: Parallel Scanning**: Refactor `AFPScanner` to support parallel chunk scanning.
2.  **Phase B: Streaming Integration**: Integrate `ParallelPageParser` with a streaming writer (avoiding the `List<StructuredField>` return).
3.  **Phase C: Ordered Buffering**: Implement an ordered "Sliding Window" buffer to ensure XML fragments are written in correct sequence.
4.  **Phase D: CLI & User Control**: Expose parallel conversion in the CLI via a `--parallel` flag.
