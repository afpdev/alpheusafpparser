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
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.DigestInputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;


public class AFPParserTest {

  public static final Logger LOG = LoggerFactory.getLogger("AFPParserTest");

  private static File[] filesSuite = {};

  @BeforeClass
  public static void onlyOnce() throws Exception {
    if (FilesSuite.getAfpFiles() != null) {
      filesSuite = FilesSuite.getAfpFiles();
    }
  }

  @Test
  public void testParsingAllTestFiles() throws Exception {
    try {
      AFPParserConfiguration pc = new AFPParserConfiguration();
      for (File afpFile : filesSuite) {

        LOG.debug("--- FILE: {}", afpFile.getAbsolutePath());

        pc.setInputStream(new FileInputStream(afpFile));

        AFPParser parser = new AFPParser(pc);

        StructuredField sf;
        do {
          sf = parser.parseNextSF();
          if (sf != null) {
            LOG.debug("StructuredField: {}", sf);
            StructuredFieldIntroducer sfi = sf.getStructuredFieldIntroducer();
            assertNotNull(sfi);
          }
        } while (sf != null);

        pc.getInputStream().close();
      }
    } catch (Exception exception) {
      LOG.error("Unable to testParsingAllTestFiles SF: {}", exception.getLocalizedMessage());
    }
  }

  @Test
  public void testAFPSerializationActualClassType() throws Exception {
    try {
      AFPParserConfiguration pc = new AFPParserConfiguration();
      pc.setParseToStructuredFieldsBaseData(false);
      File tmpFile = new File("junit_testWritingTmp.afp");

      MessageDigest mdIs = MessageDigest.getInstance("MD5");
      MessageDigest mdOs = MessageDigest.getInstance("MD5");

      for (File afpFile : filesSuite) {

        DigestInputStream dis = new DigestInputStream(new FileInputStream(afpFile), mdIs);
        pc.setInputStream(dis);

        DigestOutputStream dos = new DigestOutputStream(new FileOutputStream(tmpFile), mdOs);

        AFPParser parser = new AFPParser(pc);

        StructuredField sf;
        do {
          sf = parser.parseNextSF();
          if (sf != null) {
            LOG.debug("StructuredField: {}", sf);
            sf.writeAFP(dos, pc);

            assertArrayEquals(afpFile.getName() + " 0x" + Long.toHexString(sf.getStructuredFieldIntroducer().getFileOffset()) + " " + sf.getClass().getSimpleName(),
                    dis.getMessageDigest().digest(),
                    dos.getMessageDigest().digest()
            );

          }

        } while (sf != null);

        dos.close();
        dis.close();
        tmpFile.delete();
      }
    } catch (Exception exception) {
      LOG.error("Unable to testParsingAllTestFiles SF: {}", exception.getLocalizedMessage());
    }
  }

  @Test
  public void testAFPSerializationStructuredFieldBase() throws Exception {
    try {
      AFPParserConfiguration pc = new AFPParserConfiguration();
      pc.setParseToStructuredFieldsBaseData(true);
      File tmpFile = new File("junit_testWritingTmp.afp");

      MessageDigest mdIs = MessageDigest.getInstance("MD5");
      MessageDigest mdOs = MessageDigest.getInstance("MD5");

      for (File afpFile : filesSuite) {

        DigestInputStream dis = new DigestInputStream(new FileInputStream(afpFile), mdIs);
        pc.setInputStream(dis);

        DigestOutputStream dos = new DigestOutputStream(new FileOutputStream(tmpFile), mdOs);

        AFPParser parser = new AFPParser(pc);

        StructuredField sf;
        do {
          sf = parser.parseNextSF();
          if (sf != null) {
            LOG.debug("StructuredField: {}", sf);
            sf.writeAFP(dos, pc);
            if (!Arrays.equals(mdIs.digest(), mdOs.digest())) {

              sf.writeAFP(dos, pc);
              assertArrayEquals(afpFile.getName() + " 0x" + Long.toHexString(sf.getStructuredFieldIntroducer().getFileOffset()) + " " + sf.getClass().getSimpleName(),
                      dis.getMessageDigest().digest(),
                      dos.getMessageDigest().digest()
              );
            }

          }

        } while (sf != null);

        dos.close();
        dis.close();
        tmpFile.delete();
      }
    } catch (Exception exception) {
      LOG.error("Unable to testAFPSerializationStructuredFieldBase SF: {}", exception.getLocalizedMessage());
    }
  }
}
