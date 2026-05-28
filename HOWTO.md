# HOWTO: Use Alpheus AFP Parser

This guide provides a detailed overview of how to use the Alpheus AFP Parser library to read and process IBM AFP (Advanced Function Presentation) files.

## Command Line Interface (CLI)

The Alpheus AFP Parser includes a Command Line Interface (CLI) tool that allows you to easily convert IBM AFP files into a structured XML format. This is particularly useful for inspecting the contents of AFP files without writing any code.

### Obtaining the CLI Tool

The CLI tool is distributed as a "fat JAR" (a standalone executable JAR containing all necessary dependencies). You can obtain it in two ways:

1.  **Download from GitHub Releases:** Navigate to the [Releases](https://github.com/chatelao/alpheusafpparser/releases) page and download the `alpheus-afp-parser-cli-<version>.jar` asset from the latest release.
2.  **Build from Source:** If you have the source code, you can build the CLI JAR using Gradle:
    ```bash
    ./gradlew shadowJar
    ```
    The generated JAR will be located in `build/libs/alpheus-afp-parser-cli-<version>.jar`.

### Prerequisites

You must have Java 21 or higher installed on your system.

### Using the CLI JAR

Run the CLI tool using the following command:

```bash
java -jar alpheus-afp-parser-cli-<version>.jar [-d|--directory] <input-afp-file/dir> [output-xml-file]
```

- **`<input-afp-file/dir>`**: The path to the input AFP file or directory you wish to process.
- **`[output-xml-file]`**: (Optional) The path where the generated XML output will be saved. If this argument is omitted and you are not in directory mode, the XML content will be printed directly to the standard output (console).
- **`-d`, `--directory`**: (Optional) Convert all `.afp` files in the specified directory to XML. In this mode, the output files are created in the same directory with `.xml` appended to the original filenames.

### Examples

**Convert an AFP file and view the XML in the console:**

```bash
java -jar alpheus-afp-parser-cli-<version>.jar my_document.afp
```

**Convert an AFP file and save the result to a file:**

```bash
java -jar alpheus-afp-parser-cli-<version>.jar my_document.afp my_document.xml
```

**Batch convert all AFP files in a directory:**

```bash
java -jar alpheus-afp-parser-cli-<version>.jar --directory ./afp_files/
```
In this example, every file ending in `.afp` in `./afp_files/` will be converted to a corresponding `.xml` file (e.g., `doc1.afp` becomes `doc1.afp.xml`).

### XML Output Format

The CLI tool generates an XML representation where:
- The root element is `<AFPDocument>`.
- Each AFP Structured Field is represented as a child element (e.g., `<BDT_BeginDocument>`, `<PGD_PageDescriptor>`).
- Fields within each Structured Field are mapped to XML elements.
- The `structuredFieldIntroducer` (containing length, type, and flags) is included for every field.

## Supported Architectures

Alpheus AFP Parser provides comprehensive support for the following AFP architectures:
- **MO:DCA**: Mixed Object Document Content Architecture
- **BCOCA**: Bar Code Object Content Architecture
- **CMOCA**: Color Management Object Content Architecture
- **FOCA**: Font Object Content Architecture
- **GOCA**: Graphics Object Content Architecture
- **IOCA**: Image Object Content Architecture
- **PTOCA**: Presentation Text Object Content Architecture
- **Line Data**: Support for traditional Line Data structures.

## Installation

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>com.github.chatelao</groupId>
  <artifactId>alpheusafpparser</artifactId>
  <version>${version}</version>
</dependency>
```

### Gradle

Add the following to your `build.gradle`:

```gradle
dependencies {
    compile 'com.github.chatelao:alpheusafpparser:${version}'
}
```

## Basic Usage

The core of the library revolves around the `AFPParser` and `AFPParserConfiguration` classes.

### Reading an AFP File

To parse an AFP file, you need to create an `AFPParserConfiguration`, set the input stream, and then use the `AFPParser` to iterate through the structured fields.

```java
import com.mgz.afp.base.StructuredField;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import java.io.FileInputStream;

public class SimpleParser {
    public static void main(String[] args) throws Exception {
        AFPParserConfiguration config = new AFPParserConfiguration();
        try (FileInputStream fis = new FileInputStream("example.afp")) {
            config.setInputStream(fis);
            AFPParser parser = new AFPParser(config);

            StructuredField sf;
            while ((sf = parser.parseNextSF()) != null) {
                // Process the structured field
                System.out.println("Found Structured Field: " + sf.getStructuredFieldIntroducer().getSFTypeID());

                // Monitor parsing progress
                System.out.println("Bytes read: " + parser.getCountReadByte());
                System.out.println("Fields built: " + parser.getNrOfSFBuilt());
                System.out.println("Errors encountered: " + parser.getNrOfSFBuiltWithErrors());
            }
        }
    }
}
```

## Configuration Options

`AFPParserConfiguration` allows you to customize the parser's behavior.

### Character Set

Set the charset used to decode text within the AFP stream (e.g., in TLEs or NOPs). The default is `CP500`.

```java
config.setAfpCharSet(Charset.forName("Cp1141")); // Example: German with Euro
```

### Stateful Encoding

For architectures like PTOCA and GOCA that support dynamic character set switching, you can register Local ID (LID) to Charset mappings.

```java
config.addCodedFontCharsetMapping((short) 0x01, Charset.forName("Cp500"));
config.addCodedFontCharsetMapping((short) 0x02, Charset.forName("Cp273"));
```

### Buffer Size

Set the internal buffer size for reading the input stream (in bytes). Default is 100KB.

```java
config.setBufferSize(512 * 1024); // 512KB
```

### Shallow Parsing

If memory is a concern, you can enable shallow parsing. In this mode, the parser only reads the `StructuredFieldIntroducer`. The full payload is only loaded when `AFPParser.reload(sf)` is called.

```java
config.setBuildShallow(true);
```

### Raw Parsing (BaseData mode)

If you are dealing with non-compliant AFP files or just need the raw data, you can set the parser to treat all fields as raw data blocks.

```java
config.setParseToStructuredFieldsBaseData(true);
```

## XML Export

Alpheus provides a utility to export structured fields or entire documents to XML.

### Exporting an Individual Structured Field

```java
import com.mgz.xml.AFP2XMLWriter;
// ...
AFP2XMLWriter.writeXML(System.out, sf, config);
```

### Exporting a Full AFPDocument

```java
import com.mgz.afp.base.AFPDocument;
import com.mgz.xml.AFP2XMLWriter;
// ...
AFPDocument doc = new AFPDocument();
// Add fields to doc as they are parsed...
AFP2XMLWriter.writeXML(System.out, doc);
```

## Diagnostic Output

For debugging purposes, you can generate a human-readable string representation of any structured field using `AFPWriterHumanReadable`.

```java
import com.mgz.afp.writer.AFPWriterHumanReadable;
// ...
AFPWriterHumanReadable writer = new AFPWriterHumanReadable();
String diagnostic = writer.writeSF(sf);
System.out.println(diagnostic);
```

## Error Handling

By default, the parser throws an `AFPParserException` when it encounters an error in the AFP stream. You can change this behavior to continue parsing even if errors occur.

```java
config.setEscalateParsingErrors(false);
```

When `setEscalateParsingErrors(false)` is set, `parseNextSF()` will return an instance of `StructuredFieldErrornouslyBuilt` instead of throwing an exception. You can inspect this object to get details about the error and the raw data of the problematic field.

## Utilities

### Heuristic Human-Readability Check

The `UtilCharacterEncoding` class provides a utility to determine if a byte array contains primarily human-readable characters when decoded with a specific charset.

```java
import com.mgz.util.UtilCharacterEncoding;
import java.nio.charset.Charset;
// ...
boolean readable = UtilCharacterEncoding.isHumanReadable(data, Charset.forName("Cp500"));
```
