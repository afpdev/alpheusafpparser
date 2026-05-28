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
package com.mgz.afp.enums;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.StructuredFieldIntroducer;
import com.mgz.afp.parser.AFPParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StructuredFieldTest {
  @Test
  public void testAllSFTypeIDDefined() {
    for (SFTypeID a : SFTypeID.values()) {
      if (a == SFTypeID.Undefined) {
        continue;
      }
      assertFalse(a.getSfClass() == SFClass.Undefined, a.name() + "'s SFClass is undefined.");
      assertFalse(a.getSfType() == SFType.Undefined, a.name() + "'s SFType is undefined.");
      assertFalse(a.getSfCategory() == SFCategory.Undefined, a.name() + "'s SGCategory is undefined.");
    }
  }

  @Test
  public void testUniqueSFTypeIDComponentes() {
    for (SFTypeID a : SFTypeID.values()) {
      for (SFTypeID b : SFTypeID.values()) {
        if (a == b) {
          continue;
        }
        assertFalse(
            a.getSfClass() == b.getSfClass()
                && a.getSfType() == b.getSfType()
                && a.getSfCategory() == b.getSfCategory(),
            a.name() + "=" + b.name() + ":"
                + " class:" + a.getSfClass() + "==" + b.getSfClass()
                + " type:" + a.getSfType() + "==" + b.getSfType()
                + " cat:" + a.getSfCategory() + "==" + b.getSfCategory()
        );
      }
    }
  }

  @Test
  public void testNoUndefinedComponents() {
    for (SFTypeID a : SFTypeID.values()) {
      if (a == SFTypeID.Undefined) {
        continue;
      }
      assertFalse(
          a.getSfClass() == SFClass.Undefined
              || a.getSfType() == SFType.Undefined
              || a.getSfCategory() == SFCategory.Undefined,
          "Undefined: " + a.name() + ":"
              + " class:" + a.getSfClass()
              + " type:" + a.getSfType()
              + " cat:" + a.getSfCategory()
      );

    }
  }

  @Test
  public void testSFInstanciation() {
    int i = 0;
    for (SFTypeID sfTypeID : SFTypeID.values()) {
      StructuredFieldIntroducer sfi = new StructuredFieldIntroducer();
      sfi.setSFTypeID(sfTypeID);
      StructuredField sf1 = AFPParser.createSFInstance(sfi);

      assertTrue(sf1.getClass().getSimpleName().equals(sfTypeID.name()), i + ": " + sf1.getClass().getSimpleName() + " != " + sfTypeID.name());
      i++;
    }
  }

}
