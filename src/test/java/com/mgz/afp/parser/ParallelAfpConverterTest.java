package com.mgz.afp.parser;

import com.mgz.afp.base.StructuredField;
import com.mgz.xml.AfpStreamingXmlWriter;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelAfpConverterTest {

  @Test
  void testParallelConversionConsistency() throws Exception {
    File afpFile = new File("src/test/resources/afp/afp-goca-reference-03/Chapter_4.afp");
    if (!afpFile.exists()) {
      return;
    }

    // 1. Sequential conversion
    ByteArrayOutputStream seqOut = new ByteArrayOutputStream();
    AFPParserConfiguration seqConfig = new AFPParserConfiguration();
    seqConfig.setAFPFile(afpFile);
    AFPParser seqParser = new AFPParser(seqConfig);
    try (AfpStreamingXmlWriter writer = new AfpStreamingXmlWriter(seqOut)) {
      StructuredField sf;
      while ((sf = seqParser.parseNextSF()) != null) {
        writer.writeField(sf);
        sf.release();
      }
    }

    // 2. Parallel conversion
    ByteArrayOutputStream parOut = new ByteArrayOutputStream();
    AFPParserConfiguration parConfig = new AFPParserConfiguration();
    parConfig.setAFPFile(afpFile);
    ParallelAfpConverter converter = new ParallelAfpConverter(parConfig, 4, false, null);
    converter.convert(parOut);

    // 3. Compare XML output
    assertEquals(seqOut.toString("UTF-8").trim(), parOut.toString("UTF-8").trim(), "XML output should be identical");
  }
}
