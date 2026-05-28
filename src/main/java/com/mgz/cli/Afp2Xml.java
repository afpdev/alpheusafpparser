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

package com.mgz.cli;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.MnemonicPerformanceMonitor;
import com.mgz.xml.AfpStreamingXmlWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Command line utility to convert AFP files to XML.
 */
public class Afp2Xml {
  /**
   * Main entry point for the CLI.
   *
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    System.exit(execute(args));
  }

  /**
   * Executes the CLI logic.
   *
   * @param args Command line arguments.
   * @return Exit code.
   */
  public static int execute(String[] args) {
    if (args.length < 1) {
      printUsage(System.out);
      return 0;
    }

    var isDirectoryMode = false;
    var useJackson = false;
    var measure = false;
    var ptxDebug = false;
    var parallel = false;
    var threadCount = 0;
    String inputPath = null;
    String outputPath = null;
    String xpathExpression = null;

    for (var i = 0; i < args.length; i++) {
      var arg = args[i];
      switch (arg) {
        case "-h", "--help" -> {
          printUsage(System.out);
          return 0;
        }
        case "-d", "--directory" -> {
          isDirectoryMode = true;
          if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
            inputPath = args[++i];
          }
        }
        case "-x", "--xpath" -> {
          if (i + 1 < args.length) {
            xpathExpression = args[++i];
          } else {
            System.err.println("Error: --xpath requires an expression.");
            printUsage(System.err);
            return 1;
          }
        }
        case "-j", "--jackson" -> {
          useJackson = true;
        }
        case "-m", "--measure" -> {
          measure = true;
        }
        case "--ptx-debug" -> {
          ptxDebug = true;
        }
        case "-p", "--parallel" -> {
          parallel = true;
        }
        case "-t", "--threads" -> {
          if (i + 1 < args.length) {
            try {
              threadCount = Integer.parseInt(args[++i]);
            } catch (NumberFormatException e) {
              System.err.println("Error: --threads requires an integer.");
              return 1;
            }
          } else {
            System.err.println("Error: --threads requires a value.");
            return 1;
          }
        }
        default -> {
          if (arg.startsWith("-") && !"-".equals(arg)) {
            System.err.println("Unknown option: " + arg);
            printUsage(System.err);
            return 1;
          }
          if (inputPath == null) {
            inputPath = arg;
          } else if (outputPath == null) {
            outputPath = arg;
          } else {
            System.err.println("Too many arguments.");
            printUsage(System.err);
            return 1;
          }
        }
      }
    }

    if (inputPath == null) {
      printUsage(System.err);
      return 1;
    }

    if (measure) {
      MnemonicPerformanceMonitor.setEnabled(true);
    }
    if (ptxDebug) {
      com.mgz.util.PTXPerformanceMonitor.setEnabled(true);
    }

    try {
      var input = new File(inputPath);
      if (!input.exists()) {
        System.err.println("Input not found: " + inputPath);
        return 1;
      }

      if (input.isDirectory()) {
        isDirectoryMode = true;
      }

      var isTxt = xpathExpression != null && xpathExpression.endsWith("/text()");
      var extension = isTxt ? ".txt" : ".xml";

      if (isDirectoryMode) {
        if (!input.isDirectory()) {
          System.err.println("Input is not a directory: " + inputPath);
          return 1;
        }

        var files = input.listFiles((dir, name) -> name.toLowerCase().endsWith(".afp"));
        if (files == null || files.length == 0) {
          System.out.println("No .afp files found in directory: " + inputPath);
          return 0;
        }

        if (threadCount <= 0) {
          threadCount = Runtime.getRuntime().availableProcessors();
        }

        System.out.println("Processing " + files.length + " files using " + threadCount + " threads...");

        var hasErrors = new AtomicBoolean(false);
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        try {
          for (var f : files) {
            final String finalXpath = xpathExpression;
            final boolean finalJackson = useJackson;
            final boolean finalPtxDebug = ptxDebug;
            final String finalOutputPath = outputPath;
            executor.submit(() -> {
              var outputFileName = f.getName() + extension;
              File outputFile;
              if (finalOutputPath != null && !"-".equals(finalOutputPath)) {
                outputFile = new File(finalOutputPath, outputFileName);
              } else {
                outputFile = new File(f.getParentFile(), outputFileName);
              }

              try {
                if ("-".equals(finalOutputPath)) {
                  var baos = new java.io.ByteArrayOutputStream();
                  convertToXml(f, baos, finalXpath, finalJackson, finalPtxDebug, false, 0);
                  synchronized (System.out) {
                    baos.writeTo(System.out);
                    System.out.flush();
                  }
                } else {
                  try (var fos = new FileOutputStream(outputFile)) {
                    convertToXml(f, fos, finalXpath, finalJackson, finalPtxDebug, false, 0);
                  }
                  System.out.println("Export successful: " + outputFile.getPath());
                }
              } catch (Exception e) {
                var msg = e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
                synchronized (System.err) {
                  System.err.println("[" + Thread.currentThread().getName() + "] Error processing file "
                      + f.getName() + ": " + msg);
                }
                hasErrors.set(true);
              } finally {
                MnemonicPerformanceMonitor.merge();
                com.mgz.util.PTXPerformanceMonitor.merge();
              }
            });
          }
        } finally {
          executor.shutdown();
          if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
            System.err.println("Timeout waiting for tasks to complete.");
            hasErrors.set(true);
          }
        }
        return hasErrors.get() ? 1 : 0;
      } else {
        if ("-".equals(outputPath)) {
          convertToXml(input, System.out, xpathExpression, useJackson, ptxDebug, parallel, threadCount);
        } else {
          var outputFilePath = outputPath != null ? outputPath : inputPath + extension;
          var outputFile = new File(outputFilePath);
          try (var fos = new FileOutputStream(outputFile)) {
            convertToXml(input, fos, xpathExpression, useJackson, ptxDebug, parallel, threadCount);
          }
          System.out.println("Export successful: " + outputFile.getPath());
        }
        return 0;
      }
    } catch (Exception e) {
      System.err.println("Error during XML export: " + e.getMessage());
      e.printStackTrace();
      return 1;
    } finally {
      if (measure) {
        MnemonicPerformanceMonitor.printSummary();
      }
      if (ptxDebug) {
        com.mgz.util.PTXPerformanceMonitor.printSummary();
      }
    }
  }

  private static void printUsage(PrintStream out) {
    out.println("Usage: java -jar alpheus-afp-parser-cli.jar "
        + "[-d|--directory <dir>] [-x|--xpath <expression>] [-j|--jackson] [-m|--measure] "
        + "[--ptx-debug] [-p|--parallel] [-t|--threads <n>] <input-afp-file/dir> [output-xml-file]");
    out.println("Options:");
    out.println("  -d, --directory <dir>     Convert all .afp files in the specified directory "
        + "to XML.");
    out.println("                            If a directory is provided as a positional "
        + "argument, directory mode is enabled automatically.");
    out.println("  -x, --xpath <expression>  Filter the generated XML using an XPath expression.");
    out.println("  -j, --jackson             Use Jackson XML for streaming (experimental).");
    out.println("  -m, --measure             Measure and sum up the time needed to parse and "
        + "write each mnemonic.");
    out.println("  --ptx-debug               Detailed PTX/PTOCA performance analysis.");
    out.println("  -p, --parallel            Enable parallel conversion for single files.");
    out.println("  -t, --threads <n>         Number of threads for parallel processing.");
    out.println("                            Defaults to the number of available processors.");
    out.println("  -h, --help                Show this help message.");
  }

  private static void convertToXml(File inputFile, java.io.OutputStream os, String xpathExpression,
      boolean useJackson, boolean ptxDebug, boolean parallel, int threadCount) throws Exception {
    var config = new AFPParserConfiguration();
    config.setAFPFile(inputFile);
    config.setEscalateParsingErrors(false);
    config.setPtxDebug(ptxDebug);

    if (parallel) {
      var converter = new com.mgz.afp.parser.ParallelAfpConverter(config, threadCount, useJackson, xpathExpression);
      converter.convert(os);
      return;
    }

    var parser = new AFPParser(config);
    try {
      try (var writer = useJackson
          ? (AutoCloseable) new com.mgz.xml.AfpJacksonXmlWriter(os, xpathExpression)
          : (AutoCloseable) new AfpStreamingXmlWriter(os, xpathExpression)) {
        StructuredField sf;
        while ((sf = parser.parseNextSF()) != null) {
          if (sf instanceof com.mgz.afp.base.StructuredFieldErrornouslyBuilt errSf) {
            synchronized (System.err) {
              System.err.println("[" + Thread.currentThread().getName() + "] Error parsing file "
                  + inputFile.getName() + ": " + errSf.getErrorMessage());
            }
          }
          if (writer instanceof com.mgz.xml.AfpJacksonXmlWriter jacksonWriter) {
            jacksonWriter.writeField(sf);
          } else {
            ((AfpStreamingXmlWriter) writer).writeField(sf);
          }
          sf.release();
        }
      }
      if (parser.getNrOfSFBuiltWithErrors() > 0) {
        throw new Exception("Failed to process " + parser.getNrOfSFBuiltWithErrors()
            + " structured fields correctly.");
      }
    } finally {
      parser.quitParsing();
    }
  }
}
