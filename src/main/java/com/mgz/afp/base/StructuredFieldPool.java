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

import com.mgz.afp.enums.SFTypeID;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A pool for {@link StructuredField} objects to reduce garbage collection overhead.
 * Uses a two-tier strategy: L1 (ThreadLocal) and L2 (Global Concurrent Queues).
 */
public final class StructuredFieldPool {

  private static final int L1_CAPACITY_PER_TYPE = 16;
  private static final ConcurrentHashMap<SFTypeID, ConcurrentLinkedQueue<StructuredField>> L2_POOLS =
      new ConcurrentHashMap<>();

  private static final ThreadLocal<Map<SFTypeID, Deque<StructuredField>>> L1_POOLS =
      ThreadLocal.withInitial(() -> new EnumMap<>(SFTypeID.class));

  private StructuredFieldPool() {
    // Utility class
  }

  /**
   * Acquires a {@link StructuredField} from the pool for the specified type.
   *
   * @param type the structured field type ID
   * @return a {@link StructuredField} instance, or null if the pool is empty
   */
  public static StructuredField acquire(SFTypeID type) {
    if (type == null) {
      return null;
    }

    Map<SFTypeID, Deque<StructuredField>> l1Map = L1_POOLS.get();
    Deque<StructuredField> l1 = l1Map.get(type);
    StructuredField sf = (l1 != null) ? l1.pollFirst() : null;

    if (sf == null) {
      ConcurrentLinkedQueue<StructuredField> l2 = L2_POOLS.get(type);
      if (l2 != null) {
        sf = l2.poll();
      }
    }

    if (sf != null) {
      sf.reset();
    }
    return sf;
  }

  /**
   * Releases a {@link StructuredField} back to the pool.
   *
   * @param sf   the instance to release
   * @param type the structured field type ID
   */
  public static void release(StructuredField sf, SFTypeID type) {
    if (sf != null && type != null) {
      Map<SFTypeID, Deque<StructuredField>> l1Map = L1_POOLS.get();
      Deque<StructuredField> l1 = l1Map.computeIfAbsent(type, k -> new ArrayDeque<>(L1_CAPACITY_PER_TYPE));

      if (l1.size() < L1_CAPACITY_PER_TYPE) {
        l1.addFirst(sf);
      } else {
        L2_POOLS.computeIfAbsent(type, k -> new ConcurrentLinkedQueue<>())
            .offer(sf);
      }
    }
  }
}
