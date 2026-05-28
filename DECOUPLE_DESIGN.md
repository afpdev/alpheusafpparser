# Decoupling AFP Parser from XML Generation - Design

## Module Structure

The decoupling will be centered around a new interface and the refactoring of existing output-specific classes.

### 1. The `StructuredFieldHandler` Interface
Located in `com.mgz.afp.base.handler` (or similar package), this interface defines the core contract.

```java
public interface StructuredFieldHandler extends AutoCloseable {
    /**
     * Processes a single structured field.
     * The handler is responsible for any serialization or transformation.
     */
    void handle(StructuredField sf) throws Exception;

    /**
     * Called when the processing is complete.
     */
    @Override
    void close() throws Exception;
}
```

### 2. Refactoring XML Writers
The existing `AfpStreamingXmlWriter` and `AfpJacksonXmlWriter` will be modified to implement `StructuredFieldHandler`.

-   **`AfpStreamingXmlWriter implements StructuredFieldHandler`**
-   **`AfpJacksonXmlWriter implements StructuredFieldHandler`**

The `writeField(sf)` methods will be renamed or aliased to `handle(sf)`.

### 3. High-Performance Orchestration (Parallel Processing)
The `ParallelAfpConverter` currently has hardcoded logic to instantiate XML writers. To decouple this, we introduce a `HandlerFactory`.

```java
public interface HandlerFactory {
    /**
     * Creates a new handler instance.
     * In parallel mode, this is called once per worker thread.
     */
    StructuredFieldHandler createHandler(OutputStream os, boolean fragmentMode) throws Exception;
}
```

### 4. Changes to `ParallelAfpConverter`
`ParallelAfpConverter` will be updated to accept a `HandlerFactory` instead of booleans like `useJackson`.

```java
public class ParallelAfpConverter {
    private final HandlerFactory handlerFactory;
    // ...
    public void convert(OutputStream out) throws Exception {
        // Master handler for preamble
        try (StructuredFieldHandler masterHandler = handlerFactory.createHandler(out, false)) {
            // ... parse and call masterHandler.handle(sf) ...
        }
    }
}
```

Within the `PageConversionTask` (the worker thread), a new handler is created for each thread:

```java
// Inside PageConversionTask.call()
try (StructuredFieldHandler handler = handlerFactory.createHandler(baos, true)) {
    while ((sf = parser.parseNextSF()) != null) {
        handler.handle(sf);
        sf.release();
    }
}
```

### 5. PDF Integration (Future)
To implement the high-performance PDF generator:
1.  Implement `PdfHandler implements StructuredFieldHandler`.
2.  Implement `PdfHandlerFactory implements HandlerFactory`.
3.  Pass `PdfHandlerFactory` to `ParallelAfpConverter`.

## Class Diagram (Conceptual)

```puml
interface StructuredFieldHandler {
    +handle(sf: StructuredField)
}

interface HandlerFactory {
    +createHandler(os: OutputStream, fragment: boolean): StructuredFieldHandler
}

class AfpStreamingXmlWriter implements StructuredFieldHandler
class AfpJacksonXmlWriter implements StructuredFieldHandler
class AfpPdfWriter implements StructuredFieldHandler

class ParallelAfpConverter {
    -handlerFactory: HandlerFactory
    +convert(os: OutputStream)
}

ParallelAfpConverter --> HandlerFactory
HandlerFactory ..> StructuredFieldHandler
```

## Performance Considerations
-   **Interface Overhead:** In Java, calling an interface method is extremely fast (JIT-optimized). The overhead is negligible compared to the cost of parsing the AFP structured field itself.
-   **Object Pooling:** The `StructuredField` objects are still released by the orchestrator after the handler returns, ensuring the object pools in `StructuredFieldPool` and `SfiPool` remain effective.
-   **No Allocation in Hot Path:** The handler factory creates handlers once per thread/preamble, not once per field. The `handle(sf)` method remains allocation-free except for the specific output requirements (e.g., XML serialization).
