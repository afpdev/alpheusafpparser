/*
Copyright 2024 Rudolf Fiala

This file is part of Alpheus AFP Parser.

Alpheus AFP Parser is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Alpheus AFP Parser is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Alpheus AFP Parser.  If not, see <http://www.gnu.org/licenses/>
*/

package com.mgz.afp.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mgz.afp.base.StructuredField;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParallelPageParserTest {

  @Test
  void testParallelParsingConsistency() throws Exception {
    File afpFile = new File("src/test/resources/afp/afp-goca-reference-03/Chapter_4.afp");
    if (!afpFile.exists()) {
      return; // Skip if file not found in environment
    }

    // 1. Sequential parsing
    AFPParserConfiguration seqConfig = new AFPParserConfiguration();
    seqConfig.setAFPFile(afpFile);
    AFPParser seqParser = new AFPParser(seqConfig);
    List<StructuredField> seqFields = new ArrayList<>();
    StructuredField sf;
    while ((sf = seqParser.parseNextSF()) != null) {
      seqFields.add(sf);
    }

    // 2. Parallel parsing
    AFPParserConfiguration parConfig = new AFPParserConfiguration();
    parConfig.setAFPFile(afpFile);
    ParallelPageParser parParser = new ParallelPageParser(parConfig);
    List<StructuredField> parFields = parParser.parseParallel();

    // 3. Compare results
    assertNotNull(seqFields);
    assertNotNull(parFields);
    assertEquals(seqFields.size(), parFields.size(), "Number of fields should be the same");

    for (int i = 0; i < seqFields.size(); i++) {
      assertEquals(seqFields.get(i).getStructuredFieldIntroducer().getSFTypeID(),
          parFields.get(i).getStructuredFieldIntroducer().getSFTypeID(),
          "SFTypeID should match at index " + i);
      assertEquals(seqFields.get(i).getStructuredFieldIntroducer().getFileOffset(),
          parFields.get(i).getStructuredFieldIntroducer().getFileOffset(),
          "File offset should match at index " + i);
    }
  }
}
