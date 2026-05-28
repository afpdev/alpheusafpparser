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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A pool for {@link IRepeatingGroup} objects to reduce garbage collection overhead.
 * Uses a two-tier strategy: L1 (ThreadLocal) and L2 (Global Concurrent Queues).
 */
public final class RepeatingGroupPool {

  private static final int L1_CAPACITY_PER_TYPE = 16;
  private static final ConcurrentHashMap<Class<? extends IRepeatingGroup>, ConcurrentLinkedQueue<IRepeatingGroup>> L2_POOLS =
      new ConcurrentHashMap<>();
  private static final ThreadLocal<Map<Class<? extends IRepeatingGroup>, Deque<IRepeatingGroup>>> L1_POOLS =
      ThreadLocal.withInitial(HashMap::new);

  private RepeatingGroupPool() {
    // Utility class
  }

  /**
   * Acquires an {@link IRepeatingGroup} from the pool for the specified class.
   *
   * @param clazz the repeating group class
   * @param <T>   the type of the repeating group
   * @return an {@link IRepeatingGroup} instance, or null if the pool is empty
   */
  @SuppressWarnings("unchecked")
  public static <T extends IRepeatingGroup> T acquire(Class<T> clazz) {
    if (clazz == null) {
      return null;
    }

    Map<Class<? extends IRepeatingGroup>, Deque<IRepeatingGroup>> l1Map = L1_POOLS.get();
    Deque<IRepeatingGroup> l1 = l1Map.get(clazz);
    T rg = (l1 != null) ? (T) l1.pollFirst() : null;

    if (rg == null) {
      ConcurrentLinkedQueue<IRepeatingGroup> l2 = L2_POOLS.get(clazz);
      if (l2 != null) {
        rg = (T) l2.poll();
      }
    }

    if (rg != null) {
      rg.reset();
    }
    return rg;
  }

  /**
   * Releases an {@link IRepeatingGroup} back to the pool.
   *
   * @param rg the instance to release
   */
  public static void release(IRepeatingGroup rg) {
    if (rg != null) {
      Class<? extends IRepeatingGroup> clazz = rg.getClass();
      Map<Class<? extends IRepeatingGroup>, Deque<IRepeatingGroup>> l1Map = L1_POOLS.get();
      Deque<IRepeatingGroup> l1 = l1Map.computeIfAbsent(clazz, k -> new ArrayDeque<>(L1_CAPACITY_PER_TYPE));

      if (l1.size() < L1_CAPACITY_PER_TYPE) {
        l1.addFirst(rg);
      } else {
        L2_POOLS.computeIfAbsent(clazz, k -> new ConcurrentLinkedQueue<>())
            .offer(rg);
      }
    }
  }

  /**
   * Returns the current size of the L2 pool for the specified class.
   *
   * @param clazz the repeating group class
   * @return the L2 pool size
   */
  public static int size(Class<? extends IRepeatingGroup> clazz) {
    ConcurrentLinkedQueue<IRepeatingGroup> pool = L2_POOLS.get(clazz);
    return pool != null ? pool.size() : 0;
  }
}
