package com.mgz.test.acceptance;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.modca.BNG_BeginNamedPageGroup;
import com.mgz.afp.modca.BPG_BeginPage;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CountPageGroupsAndPagesInPageGroup {

  public static final Logger LOG = LoggerFactory.getLogger("CountPageGroupsAndPagesInPageGroup");

  public static void main(String[] args) throws AFPParserException, IOException {

    InputStream is = new BufferedInputStream(new FileInputStream(new File(args[0])), 1024 * 1024);
    AFPParserConfiguration pc = new AFPParserConfiguration();
    pc.setInputStream(is);
    AFPParser parser = new AFPParser(pc);

    BNG_BeginNamedPageGroup currentBNG = null;
    int pageGroupCount = 0;
    int pageCount = 0;

    StructuredField sf = null;
    do {
      sf = parser.parseNextSF();
      if (sf != null) {
        if (sf instanceof BNG_BeginNamedPageGroup) {
          if (currentBNG != null) {
            String message = "PageGroup #" + pageGroupCount + " '" + currentBNG.getName() != null ? currentBNG.getName() : "" + "' has " + pageCount + " pages.";
            LOG.info(message);
          }
          currentBNG = (BNG_BeginNamedPageGroup) sf;
          pageGroupCount++;
          pageCount = 0;
        } else if (sf instanceof BPG_BeginPage) {
          pageCount++;
        }
      }
    } while (sf != null);

    // Print out last Page Group.
    if (currentBNG != null) {
      String message = "PageGroup #" + pageGroupCount + " '" + currentBNG.getName() != null ? currentBNG.getName() : "" + "' has " + pageCount + " pages.";
      LOG.info(message);
    }

    // Close input stream.
    is.close();
  }

}
