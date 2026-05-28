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

package com.mgz.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.util.TreeMap;

/**
 * Collects and writes XML fragments in the correct order.
 * It uses a sliding window (TreeMap) to handle out-of-order results from parallel threads.
 */
public class OrderedResultCollector {

  private final OutputStream out;
  private final TreeMap<Integer, byte[]> buffer = new TreeMap<>();
  private int nextSequence = 0;

  /**
   * Constructs an OrderedResultCollector.
   *
   * @param out the output stream to write ordered results to
   */
  public OrderedResultCollector(OutputStream out) {
    this.out = out;
  }

  /**
   * Adds a fragment to the collector.
   *
   * @param sequence the sequence number of the fragment
   * @param data the fragment data
   * @throws IOException if writing to the output stream fails
   */
  public synchronized void put(int sequence, byte[] data) throws IOException {
    buffer.put(sequence, data);
    while (!buffer.isEmpty() && buffer.firstKey() == nextSequence) {
      byte[] fragment = buffer.remove(nextSequence);
      out.write(fragment);
      nextSequence++;
    }
    out.flush();
  }

  /**
   * Checks if all fragments have been written.
   *
   * @return true if the buffer is empty, false otherwise
   */
  public synchronized boolean isComplete() {
    return buffer.isEmpty();
  }
}
