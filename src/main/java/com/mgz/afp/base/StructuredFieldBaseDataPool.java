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

package com.mgz.afp.base;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A pool for {@link StructuredFieldBaseData} objects to reduce garbage collection overhead.
 * Uses a two-tier strategy: L1 (ThreadLocal) and L2 (Global Concurrent Queue).
 */
public final class StructuredFieldBaseDataPool {

  private static final int L1_CAPACITY = 64;
  private static final ConcurrentLinkedQueue<StructuredFieldBaseData> L2_POOL = new ConcurrentLinkedQueue<>();
  private static final ThreadLocal<Deque<StructuredFieldBaseData>> L1_POOL =
      ThreadLocal.withInitial(() -> new ArrayDeque<>(L1_CAPACITY));

  private StructuredFieldBaseDataPool() {
    // Utility class
  }

  /**
   * Acquires a {@link StructuredFieldBaseData} from the pool or creates a new one.
   *
   * @return a {@link StructuredFieldBaseData} instance
   */
  public static StructuredFieldBaseData acquire() {
    Deque<StructuredFieldBaseData> l1 = L1_POOL.get();
    StructuredFieldBaseData sf = l1.pollFirst();
    if (sf == null) {
      sf = L2_POOL.poll();
    }

    if (sf == null) {
      return new StructuredFieldBaseData();
    }
    sf.reset();
    return sf;
  }

  /**
   * Releases a {@link StructuredFieldBaseData} back to the pool.
   *
   * @param sf the instance to release
   */
  public static void release(StructuredFieldBaseData sf) {
    if (sf != null) {
      Deque<StructuredFieldBaseData> l1 = L1_POOL.get();
      if (l1.size() < L1_CAPACITY) {
        l1.addFirst(sf);
      } else {
        L2_POOL.offer(sf);
      }
    }
  }

  /**
   * Returns the current size of the L2 pool.
   *
   * @return the L2 pool size
   */
  public static int size() {
    return L2_POOL.size();
  }
}
