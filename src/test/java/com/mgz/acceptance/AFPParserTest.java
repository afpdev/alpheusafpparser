/*
Copyright 2015 Rudolf Fiala

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
package com.mgz.acceptance;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.StructuredFieldIntroducer;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import static org.junit.Assert.*;

/*
 Links to publicly available AFP files that can be used for testing:

 ftp://public.dhe.ibm.com/printers/products/fonts/outline/vseoutln.afp
 ftp://public.dhe.ibm.com/printers/products/fonts/outline/fontoutl.afp
 ftp://public.dhe.ibm.com/printers/products/fonts/postal/postnetq.afp
 ftp://public.dhe.ibm.com/printers/products/fonts/progdirs/3828micr.afp
 ftp://public.dhe.ibm.com/printers/products/fonts/progdirs/colv1as4.afp
 ftp://public.dhe.ibm.com/printers/products/fonts/progdirs/colv1vm.afp
 ftp://public.dhe.ibm.com/printers/products/fonts/progdirs/colv1vse.afp
 ftp://public.dhe.ibm.com/printers/products/fonts/progdirs/Postal.afp
 ftp://public.dhe.ibm.com/printers/products/workbench/windows/info/ddewin.afp
 ftp://public.dhe.ibm.com/printers/products/workbench/windows/info/printit.afp
 ftp://public.dhe.ibm.com/printers/products/workbench/manuals/afptech2.afp

 External font resources:

 http://afp-renderer.cvs.sourceforge.net/viewvc/afp-renderer/afp-fop-src/resources/fonts/

 If you know other publicly available AFP files, please send a link to following email
 address so I can add it to this list: afpdev@mogozine.com
*/
public class AFPParserTest {
  private static File[] filesSuite = {};

  @BeforeClass
  public static void onlyOnce() throws Exception {
    filesSuite = FilesSuite.getAfpFiles();
    assertTrue("No AFP Testiles found", filesSuite != null && filesSuite.length > 0);
  }

  @Test
  public void testParsingAllTestFiles() throws Exception {
    AFPParserConfiguration pc = new AFPParserConfiguration();

    for (File afpFile : filesSuite) {
      try {

        pc.setInputStream(new FileInputStream(afpFile));

        AFPParser parser = new AFPParser(pc);

        StructuredField sf;
        do {
          sf = parser.parseNextSF();
          if (sf != null) {
            StructuredFieldIntroducer sfi = sf.getStructuredFieldIntroducer();
            assertNotNull(sfi);
          }
        } while (sf != null);

        pc.getInputStream().close();

      } finally {
        pc.getInputStream().close();
      }
    }
  }

  @Test
  public void testAFPSerializationActualClassType() throws Exception {
    AFPParserConfiguration pc = new AFPParserConfiguration();
    pc.setParseToStructuredFieldsBaseData(false);

    ByteArrayOutputStream bytesSerialized = new ByteArrayOutputStream();

    for (File afpFile : filesSuite) {
      FileInputStream fisForParsing = new FileInputStream(afpFile);
      FileInputStream fisForReference = new FileInputStream(afpFile);

      try {
        pc.setInputStream(fisForParsing);

        AFPParser parser = new AFPParser(pc);

        StructuredField sf;
        do {
          sf = parser.parseNextSF();

          if (sf != null) {
            byte[] bytesOriginal = new byte[sf.getStructuredFieldIntroducer().getSFLength() + 1];
            fisForReference.read(bytesOriginal);

            bytesSerialized.reset();
            sf.writeAFP(bytesSerialized, pc);

            assertArrayEquals(
                afpFile.getName() + " 0x"
                    + Long.toHexString(sf.getStructuredFieldIntroducer().getFileOffset()) + " " + sf.getClass().getSimpleName() + "\n"
                    + "Original:\n" + Arrays.toString(bytesOriginal) + "\n"
                    + "Serialized:\n" + Arrays.toString(bytesSerialized.toByteArray()) + "\n",
                bytesOriginal, bytesSerialized.toByteArray()
            );
          }

        } while (sf != null);

      } finally {
        if (fisForParsing != null) {
          fisForParsing.close();
        }
        if (fisForReference != null) {
          fisForReference.close();
        }
      }
    }
  }

  @Test
  public void testAFPSerializationStructuredFieldBaseData() throws Exception {
    AFPParserConfiguration pc = new AFPParserConfiguration();
    pc.setParseToStructuredFieldsBaseData(true);

    ByteArrayOutputStream bytesSerialized = new ByteArrayOutputStream();

    for (File afpFile : filesSuite) {
      FileInputStream fisForParsing = new FileInputStream(afpFile);
      FileInputStream fisForReference = new FileInputStream(afpFile);

      try {
        pc.setInputStream(fisForParsing);

        AFPParser parser = new AFPParser(pc);

        StructuredField sf;
        do {
          sf = parser.parseNextSF();

          if (sf != null) {
            byte[] bytesOriginal = new byte[sf.getStructuredFieldIntroducer().getSFLength() + 1];
            fisForReference.read(bytesOriginal);

            bytesSerialized.reset();
            sf.writeAFP(bytesSerialized, pc);

            assertArrayEquals(
                afpFile.getName() + " 0x"
                    + Long.toHexString(sf.getStructuredFieldIntroducer().getFileOffset()) + " " + sf.getClass().getSimpleName() + "\n"
                    + "Original:\n" + Arrays.toString(bytesOriginal) + "\n"
                    + "Serialized:\n" + Arrays.toString(bytesSerialized.toByteArray()) + "\n",
                bytesOriginal, bytesSerialized.toByteArray()
            );
          }

        } while (sf != null);

      } finally {
        if (fisForParsing != null) {
          fisForParsing.close();
        }
        if (fisForReference != null) {
          fisForReference.close();
        }
      }
    }
  }
}