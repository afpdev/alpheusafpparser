package com.mgz.examples;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.modca.BNG_BeginNamedPageGroup;
import com.mgz.afp.modca.BPG_BeginPage;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This is a sample command line application that reads an AFP-File and counts all
 * named page groups and the pages within.
 */
public final class CountPageGroupsAndPagesInPageGroup {

  public static void main(String[] args) throws AFPParserException, IOException {

    if (args.length != 1) {
      System.err.println("Please provide the file name of a AFP print file.");
      System.exit(1);
    }

    if (!new File(args[0]).exists()) {
      System.err.println("The file" + args[0] + " does not exist.");
      System.exit(1);
    }

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
            printCounters(currentBNG.getName(), pageGroupCount, pageCount);
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
      printCounters(currentBNG.getName(), pageGroupCount, pageCount);
    }

    // Close input stream.
    is.close();
  }

  private static void printCounters(final String pageGroupName, final int pageGroupCount, final int pageCount) {
    StringBuilder sb = new StringBuilder();
    sb.append("PageGroup #");
    sb.append(pageGroupCount);
    sb.append(" '");
    sb.append(pageGroupName != null ? pageGroupName : "");
    sb.append("' has ");
    sb.append(pageCount);
    sb.append(" pages.");

    System.out.println(sb.toString());
  }

}
