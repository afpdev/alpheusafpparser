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

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.util.MnemonicPerformanceMonitor;
import com.mgz.util.PTXPerformanceMonitor;
import com.mgz.xml.AfpJacksonXmlWriter;
import com.mgz.xml.AfpStreamingXmlWriter;
import com.mgz.xml.OrderedResultCollector;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Orchestrates high-throughput parallel conversion of single large AFP files to XML.
 */
public class ParallelAfpConverter {

  private final AFPParserConfiguration config;
  private final int numThreads;
  private final boolean useJackson;
  private final String xpathExpression;

  /**
   * Constructs a ParallelAfpConverter.
   *
   * @param config the parser configuration
   * @param numThreads number of threads to use
   * @param useJackson whether to use Jackson XML
   * @param xpathExpression optional XPath expression for filtering
   */
  public ParallelAfpConverter(AFPParserConfiguration config, int numThreads, boolean useJackson, String xpathExpression) {
    this.config = config;
    this.numThreads = numThreads > 0 ? numThreads : Runtime.getRuntime().availableProcessors();
    this.useJackson = useJackson;
    this.xpathExpression = xpathExpression;
  }

  /**
   * Converts the AFP file to XML in parallel.
   *
   * @param out the output stream for the XML
   * @throws AFPParserException if parsing fails
   * @throws IOException if I/O fails
   */
  public void convert(OutputStream out) throws Exception {
    ByteBuffer buffer = config.getByteBuffer();
    AsynchronousFileChannel asyncChannel = null;
    if (buffer == null) {
      asyncChannel = config.getAsyncFileChannel();
    }

    if (buffer == null && asyncChannel == null) {
      throw new AFPParserException("MappedByteBuffer or AsynchronousFileChannel is required for parallel parsing.");
    }

    AFPScanner scanner = (buffer != null) ? new AFPScanner(buffer) : new AFPScanner(asyncChannel);
    List<Long> pageOffsets = scanner.findPageBoundariesParallel();

    long fileSize = (buffer != null) ? buffer.limit() : asyncChannel.size();
    long firstPageOffset = pageOffsets.isEmpty() ? fileSize : pageOffsets.get(0);

    OrderedResultCollector collector = new OrderedResultCollector(out);
    int sequence = 0;

    // Correct approach:
    // Create a master writer that is NOT in fragment mode.
    // It will write the header.
    // We will then write fragments directly to the underlying 'out' via the collector.
    // Finally, we close the master writer.

    if (useJackson) {
        try (AfpJacksonXmlWriter masterWriter = new AfpJacksonXmlWriter(out, xpathExpression, false)) {
            processPreambleAndPages(firstPageOffset, pageOffsets, fileSize, collector, sequence, masterWriter);
        }
    } else {
        try (AfpStreamingXmlWriter masterWriter = new AfpStreamingXmlWriter(out, xpathExpression, false)) {
            processPreambleAndPages(firstPageOffset, pageOffsets, fileSize, collector, sequence, masterWriter);
        }
    }
  }

  private void processPreambleAndPages(long firstPageOffset, List<Long> pageOffsets, long fileSize,
      OrderedResultCollector collector, int sequence, Object masterWriter) throws Exception {
    // 1. Parse and write Preamble sequentially
    if (firstPageOffset > 0) {
      AFPParser preambleParser = new AFPParser(config);
      while (preambleParser.getCountReadByte() < firstPageOffset) {
        StructuredField sf = preambleParser.parseNextSF();
        if (sf == null) break;
        if (masterWriter instanceof AfpJacksonXmlWriter jw) jw.writeField(sf);
        else ((AfpStreamingXmlWriter)masterWriter).writeField(sf);
        sf.release();
      }
    }

    if (pageOffsets.isEmpty()) return;

    // 2. Parallel Page Processing
    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    try {
      List<Future<Void>> futures = new ArrayList<>();
      for (int i = 0; i < pageOffsets.size(); i++) {
        long start = pageOffsets.get(i);
        long end = (i + 1 < pageOffsets.size()) ? pageOffsets.get(i + 1) : fileSize;
        futures.add(executor.submit(new PageConversionTask(config.clone(), start, end, i, collector, useJackson, xpathExpression)));
      }

      for (Future<Void> future : futures) {
        future.get();
      }
    } finally {
      executor.shutdown();
      executor.awaitTermination(1, TimeUnit.HOURS);
    }
  }

  private static class PageConversionTask implements Callable<Void> {
    private final AFPParserConfiguration taskConfig;
    private final long startOffset;
    private final long endOffset;
    private final int sequence;
    private final OrderedResultCollector collector;
    private final boolean useJackson;
    private final String xpathExpression;

    PageConversionTask(AFPParserConfiguration config, long start, long end, int seq,
        OrderedResultCollector collector, boolean useJackson, String xpath) {
      this.taskConfig = config;
      this.startOffset = start;
      this.endOffset = end;
      this.sequence = seq;
      this.collector = collector;
      this.useJackson = useJackson;
      this.xpathExpression = xpath;
    }

    @Override
    public Void call() throws Exception {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
        if (taskConfig.getByteBuffer() == null && taskConfig.getAsyncFileChannel() != null) {
          int pageSize = (int) (endOffset - startOffset);
          ByteBuffer pageData = ByteBuffer.allocateDirect(pageSize);
          Future<Integer> readFuture = taskConfig.getAsyncFileChannel().read(pageData, startOffset);
          readFuture.get();
          pageData.flip();
          taskConfig.setByteBuffer(pageData);
        }

        AFPParser parser = new AFPParser(taskConfig);
        if (taskConfig.getByteBuffer() != null && startOffset < taskConfig.getByteBuffer().limit()) {
             // If we pre-loaded, offsets in the buffer are 0-based
             // but if we are using the original mapped buffer, we need to set start position
             if (taskConfig.getByteBuffer().capacity() > (endOffset - startOffset)) {
                  parser.setNrOfBytesRead(startOffset);
             }
        }

        try (AutoCloseable writer = useJackson
            ? new AfpJacksonXmlWriter(baos, xpathExpression, true)
            : new AfpStreamingXmlWriter(baos, xpathExpression, true)) {
          StructuredField sf;
          while ((sf = parser.parseNextSF()) != null) {
            if (useJackson) ((AfpJacksonXmlWriter)writer).writeField(sf);
            else ((AfpStreamingXmlWriter)writer).writeField(sf);
            sf.release();
            if (parser.getCountReadByte() >= endOffset) break;
          }
        }
      } finally {
        MnemonicPerformanceMonitor.merge();
        PTXPerformanceMonitor.merge();
      }

      collector.put(sequence, baos.toByteArray());
      return null;
    }
  }
}
