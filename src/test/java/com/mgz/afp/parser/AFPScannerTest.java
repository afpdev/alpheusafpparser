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
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.enums.SFTypeID;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AFPScannerTest {

  @Test
  void testScanForPageBoundaries() throws Exception {
    File afpFile = new File("src/test/resources/afp/external/afplib_start.afp");
    if (!afpFile.exists()) {
      return;
    }

    try (RandomAccessFile raf = new RandomAccessFile(afpFile, "r");
         FileChannel channel = raf.getChannel()) {
      MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
      AFPScanner scanner = new AFPScanner(buffer);
      List<Long> offsets = scanner.findPageBoundaries();

      assertFalse(offsets.isEmpty(), "Should find page boundaries in afplib_start.afp");

      // Verify against full AFPParser
      AFPParserConfiguration config = new AFPParserConfiguration();
      config.setAFPFile(afpFile);
      AFPParser parser = new AFPParser(config);
      List<Long> expectedOffsets = new ArrayList<>();
      StructuredField sf;
      while ((sf = parser.parseNextSF()) != null) {
        if (sf.getStructuredFieldIntroducer().getSFTypeID() == SFTypeID.BPG_BeginPage) {
          expectedOffsets.add(sf.getStructuredFieldIntroducer().getFileOffset());
        }
      }
      assertEquals(expectedOffsets.size(), offsets.size(), "Scanner should find same number of BPGs as full parser");
      for (int i = 0; i < expectedOffsets.size(); i++) {
        assertEquals(expectedOffsets.get(i), offsets.get(i), "Offset should match at index " + i);
      }
    }
  }

  @Test
  void testScanForPageBoundariesParallel() throws Exception {
    File afpFile = new File("src/test/resources/afp/external/afplib_start.afp");
    if (!afpFile.exists()) {
      return;
    }

    try (RandomAccessFile raf = new RandomAccessFile(afpFile, "r");
         FileChannel channel = raf.getChannel()) {
      MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
      AFPScanner scanner = new AFPScanner(buffer);

      List<Long> sequentialOffsets = scanner.findPageBoundaries();
      List<Long> parallelOffsets = scanner.findPageBoundariesParallel();

      assertEquals(sequentialOffsets.size(), parallelOffsets.size(), "Sequential and parallel scan should find same number of BPGs. Expected " + sequentialOffsets + " but got " + parallelOffsets);
      for (int i = 0; i < sequentialOffsets.size(); i++) {
        assertEquals(sequentialOffsets.get(i), parallelOffsets.get(i), "Offset should match at index " + i);
      }
    }
  }
}
