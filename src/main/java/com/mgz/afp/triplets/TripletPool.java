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

package com.mgz.afp.triplets;

import com.mgz.afp.triplets.Triplet.TripletID;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A pool for {@link Triplet} objects to reduce garbage collection overhead.
 * Uses a two-tier strategy: L1 (ThreadLocal) and L2 (Global Concurrent Queues).
 */
public final class TripletPool {

  private static final int L1_CAPACITY_PER_TYPE = 32;
  private static final EnumMap<TripletID, ConcurrentLinkedQueue<Triplet>> L2_POOLS =
      new EnumMap<>(TripletID.class);

  private static final ThreadLocal<Map<TripletID, Deque<Triplet>>> L1_POOLS =
      ThreadLocal.withInitial(() -> new EnumMap<>(TripletID.class));

  static {
    for (TripletID id : TripletID.values()) {
      L2_POOLS.put(id, new ConcurrentLinkedQueue<>());
    }
  }

  private TripletPool() {
    // Utility class
  }

  /**
   * Acquires a {@link Triplet} from the pool for the specified ID.
   *
   * @param tid the triplet ID
   * @return a {@link Triplet} instance, or null if the pool is empty
   */
  public static Triplet acquire(TripletID tid) {
    if (tid == null) {
      return null;
    }

    Map<TripletID, Deque<Triplet>> l1Map = L1_POOLS.get();
    Deque<Triplet> l1 = l1Map.get(tid);
    Triplet triplet = (l1 != null) ? l1.pollFirst() : null;

    if (triplet == null) {
      ConcurrentLinkedQueue<Triplet> l2 = L2_POOLS.get(tid);
      if (l2 != null) {
        triplet = l2.poll();
      }
    }

    if (triplet != null) {
      triplet.reset();
      triplet.setTripletID(tid);
    }
    return triplet;
  }

  /**
   * Releases a {@link Triplet} back to the pool.
   *
   * @param triplet the instance to release
   */
  public static void release(Triplet triplet) {
    if (triplet != null && triplet.getTripletID() != null) {
      TripletID tid = triplet.getTripletID();
      Map<TripletID, Deque<Triplet>> l1Map = L1_POOLS.get();
      Deque<Triplet> l1 = l1Map.computeIfAbsent(tid, k -> new ArrayDeque<>(L1_CAPACITY_PER_TYPE));

      if (l1.size() < L1_CAPACITY_PER_TYPE) {
        l1.addFirst(triplet);
      } else {
        ConcurrentLinkedQueue<Triplet> l2 = L2_POOLS.get(tid);
        if (l2 != null) {
          l2.offer(triplet);
        }
      }
    }
  }
}
