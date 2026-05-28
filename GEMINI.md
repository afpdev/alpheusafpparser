# Project Conventions for AI Assistants

This document outlines the project-specific conventions, emoji usage, and documentation standards to assist AI models in understanding and maintaining the Alpheus AFP Parser codebase.

## Emoji Status Indicators

The project uses emojis across various documentation files (`ROADMAP.md`, `COVERAGE_ROADMAP.md`, `COVERAGE_GAP.md`, etc.) to provide quick visual status updates.

### Roadmap & Task Status
- ✅ `✅` **Completed**: The task or phase is fully implemented and verified.
- 🚧 `🚧` **In-Progress**: Work is currently being performed on this task.
- ⏳ `⏳` **Pending/Future**: The task is planned but work has not yet started.
- 🏗️ `🏗️` **Under Infrastructure Development**: Used for tasks involving significant infrastructure or tooling setup (e.g., Fuzzing integration).

### Coverage & Gap Analysis
- 🔴 `🔴` **Critical Gap**: Coverage is extremely low (typically < 5%) or non-existent.
- 🟡 `🟡` **Moderate Gap**: Some coverage exists but it is significantly below the target.
- ❓ `❓` **Unknown/Uninitialized**: Granular requirement coverage that has not yet been audited or tested.

## Documentation Hierarchy

Significant project initiatives follow a consistent three-file documentation structure:
1.  **`*_CONCEPT.md`**: High-level strategy, architectural decisions, and goals.
2.  **`*_DESIGN.md`**: Technical details, class diagrams, and implementation specifics.
3.  **`*_ROADMAP.md`**: Phased execution plan and progress tracking.

When a phase is completed, the roadmap is updated with ✅, and the concept document is typically updated to reflect the final "as-built" architecture.

## Testing & Requirement Traceability

- **Requirement IDs**: All new tests must explicitly reference a Requirement ID from the `/specifications/markdown/` directory (e.g., `[MODCA-3-021]`).
- **Round-Trip Methodology**: The preferred testing method for Structured Fields and Triplets is "Deep Round-Trip Verification":
    1.  Programmatic Generation of `byte[]`.
    2.  Decoding into Java object.
    3.  Validation against specifications.
    4.  Encoding back to `byte[]`.
    5.  Bit-for-bit equality check.

## Performance Monitoring

When implementing or refactoring features, ensure that performance instrumentation is maintained:
- Use `MnemonicPerformanceMonitor` for Structured Fields.
- Use `PTXPerformanceMonitor` for PTOCA control sequences.
- Always call `merge()` at the end of a processing task in parallel modes to aggregate thread-local statistics.
