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

package com.mgz.afp.ioca;

import com.mgz.afp.ioca.IPD_Segment.IPD_SegmentType;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A pool for {@link IPD_Segment} objects to reduce garbage collection overhead.
 * Uses a two-tier strategy: L1 (ThreadLocal) and L2 (Global Concurrent Queues).
 */
public final class IpdSegmentPool {

  private static final int L1_CAPACITY_PER_TYPE = 16;
  private static final EnumMap<IPD_SegmentType, ConcurrentLinkedQueue<IPD_Segment>> L2_POOLS =
      new EnumMap<>(IPD_SegmentType.class);
  private static final ThreadLocal<Map<IPD_SegmentType, Deque<IPD_Segment>>> L1_POOLS =
      ThreadLocal.withInitial(() -> new EnumMap<>(IPD_SegmentType.class));

  static {
    for (IPD_SegmentType type : IPD_SegmentType.values()) {
      L2_POOLS.put(type, new ConcurrentLinkedQueue<>());
    }
  }

  private IpdSegmentPool() {
    // Utility class
  }

  /**
   * Acquires an {@link IPD_Segment} from the pool for the specified type.
   *
   * @param type the image segment type
   * @return an {@link IPD_Segment} instance, or null if the pool is empty
   */
  public static IPD_Segment acquire(IPD_SegmentType type) {
    if (type == null) {
      return null;
    }

    Map<IPD_SegmentType, Deque<IPD_Segment>> l1Map = L1_POOLS.get();
    Deque<IPD_Segment> l1 = l1Map.get(type);
    IPD_Segment segment = (l1 != null) ? l1.pollFirst() : null;

    if (segment == null) {
      ConcurrentLinkedQueue<IPD_Segment> l2 = L2_POOLS.get(type);
      if (l2 != null) {
        segment = l2.poll();
      }
    }

    if (segment != null) {
      segment.reset();
    }
    return segment;
  }

  /**
   * Releases an {@link IPD_Segment} back to the pool.
   *
   * @param segment the instance to release
   */
  public static void release(IPD_Segment segment) {
    if (segment != null && segment.getSegmentType() != null) {
      IPD_SegmentType type = segment.getSegmentType();
      Map<IPD_SegmentType, Deque<IPD_Segment>> l1Map = L1_POOLS.get();
      Deque<IPD_Segment> l1 = l1Map.computeIfAbsent(type, k -> new ArrayDeque<>(L1_CAPACITY_PER_TYPE));

      if (l1.size() < L1_CAPACITY_PER_TYPE) {
        l1.addFirst(segment);
      } else {
        ConcurrentLinkedQueue<IPD_Segment> pool = L2_POOLS.get(type);
        if (pool != null) {
          pool.offer(segment);
        }
      }
    }
  }
}
