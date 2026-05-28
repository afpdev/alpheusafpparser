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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.SAME_THREAD)
public class SfiPoolTest {

  @Test
  public void testAcquireAndRelease() {
    // Note: SfiPool.size() only returns L2 size.
    // ThreadLocal L1 is not easily accessible for size checks without adding test-only methods.

    StructuredFieldIntroducer sfi1 = SfiPool.acquire();
    assertNotNull(sfi1);

    sfi1.setSFLength(100);
    SfiPool.release(sfi1);

    StructuredFieldIntroducer sfi2 = SfiPool.acquire();
    assertSame(sfi1, sfi2, "Should acquire the same instance from L1 pool");
    assertEquals(0, sfi2.getSFLength()); // Should be reset
  }

  @Test
  public void testMultipleAcquire() {
    StructuredFieldIntroducer sfi1 = SfiPool.acquire();
    StructuredFieldIntroducer sfi2 = SfiPool.acquire();

    assertNotSame(sfi1, sfi2);

    // L1 is a LIFO Deque (ArrayDeque.addFirst/pollFirst)
    SfiPool.release(sfi1);
    SfiPool.release(sfi2);

    StructuredFieldIntroducer sfi3 = SfiPool.acquire();
    StructuredFieldIntroducer sfi4 = SfiPool.acquire();

    assertSame(sfi2, sfi3, "Should be LIFO from L1");
    assertSame(sfi1, sfi4, "Should be LIFO from L1");
  }

  @Test
  public void testL2Spillover() {
    // This test assumes L1_CAPACITY = 64
    StructuredFieldIntroducer[] sfis = new StructuredFieldIntroducer[70];
    for (int i = 0; i < sfis.length; i++) {
        sfis[i] = SfiPool.acquire();
    }

    int initialL2Size = SfiPool.size();
    for (int i = 0; i < sfis.length; i++) {
        SfiPool.release(sfis[i]);
    }

    // 64 should be in L1, 6 should be in L2
    assertEquals(initialL2Size + 6, SfiPool.size());
  }
}
