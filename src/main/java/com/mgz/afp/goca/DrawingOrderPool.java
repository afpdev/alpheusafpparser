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

package com.mgz.afp.goca;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A pool for {@link GAD_DrawingOrder} objects to reduce garbage collection overhead.
 * Uses a two-tier strategy: L1 (ThreadLocal) and L2 (Global Concurrent Queues).
 */
public final class DrawingOrderPool {

  private static final int L1_CAPACITY_PER_TYPE = 32;
  private static final ConcurrentHashMap<Long, ConcurrentLinkedQueue<GAD_DrawingOrder>> L2_POOLS =
      new ConcurrentHashMap<>();
  private static final ThreadLocal<Map<Long, Deque<GAD_DrawingOrder>>> L1_POOLS =
      ThreadLocal.withInitial(HashMap::new);

  private DrawingOrderPool() {
    // Utility class
  }

  /**
   * Acquires a {@link GAD_DrawingOrder} from the pool for the specified type.
   *
   * @param type the drawing order type code
   * @return a {@link GAD_DrawingOrder} instance, or null if the pool is empty
   */
  public static GAD_DrawingOrder acquire(short type) {
    return acquire(type, (short) -1);
  }

  /**
   * Acquires a {@link GAD_DrawingOrder} from the pool for the specified type and qualifier.
   *
   * @param type      the drawing order type code
   * @param qualifier the qualifier for extended orders, or -1
   * @return a {@link GAD_DrawingOrder} instance, or null if the pool is empty
   */
  public static GAD_DrawingOrder acquire(short type, short qualifier) {
    long key = ((long) type << 16) | (qualifier & 0xFFFFL);
    return acquireInternal(key);
  }

  private static GAD_DrawingOrder acquireInternal(long key) {
    Map<Long, Deque<GAD_DrawingOrder>> l1Map = L1_POOLS.get();
    Deque<GAD_DrawingOrder> l1 = l1Map.get(key);
    GAD_DrawingOrder order = (l1 != null) ? l1.pollFirst() : null;

    if (order == null) {
      ConcurrentLinkedQueue<GAD_DrawingOrder> l2 = L2_POOLS.get(key);
      if (l2 != null) {
        order = l2.poll();
      }
    }

    if (order != null) {
      order.reset();
    }
    return order;
  }

  /**
   * Releases a {@link GAD_DrawingOrder} back to the pool.
   *
   * @param order the instance to release
   */
  public static void release(GAD_DrawingOrder order) {
    if (order != null) {
      short type = order.getDrawingOrderType();
      short qualifier = -1;
      if (type == (short) 0xFE) {
        if (order instanceof GAD_DrawingOrder.GLGD_LinearGradient) {
          qualifier = 0xDC;
        } else if (order instanceof GAD_DrawingOrder.GRGD_RadialGradient) {
          qualifier = 0xDD;
        } else if (order instanceof GAD_DrawingOrder.GEXO_ExtendedOrder e) {
          qualifier = e.getQualifier();
        }
      }
      long key = ((long) type << 16) | (qualifier & 0xFFFFL);

      Map<Long, Deque<GAD_DrawingOrder>> l1Map = L1_POOLS.get();
      Deque<GAD_DrawingOrder> l1 = l1Map.computeIfAbsent(key, k -> new ArrayDeque<>(L1_CAPACITY_PER_TYPE));

      if (l1.size() < L1_CAPACITY_PER_TYPE) {
        l1.addFirst(order);
      } else {
        L2_POOLS.computeIfAbsent(key, k -> new ConcurrentLinkedQueue<>())
            .offer(order);
      }
    }
  }
}
