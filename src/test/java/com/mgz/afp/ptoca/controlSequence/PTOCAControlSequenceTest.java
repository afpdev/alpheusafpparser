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
package com.mgz.afp.ptoca.controlSequence;

import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.PTOCAControlSequenceParser;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.ControlSequenceFunctionType;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.ControlSequenceIntroducer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PTOCAControlSequenceTest {

  @Test
  public void testInstantiationOfPTOCAControlSequences() throws AFPParserException {


    ControlSequenceIntroducer csi = new ControlSequenceIntroducer();
    for (ControlSequenceFunctionType csft : ControlSequenceFunctionType.values()) {
      csi.setControlSequenceFunctionType(csft);
      PTOCAControlSequence cs = PTOCAControlSequenceParser.createControlSequenceInstance(csi);
      assertEquals(csft.name(), cs.getClass().getSimpleName());
      assertFalse(csft != ControlSequenceFunctionType.Undefined && cs instanceof com.mgz.afp.ptoca.controlSequence.Undefined);

    }

  }

  @Test
  public void testUniqunessOfControlSequenceFunctionType() {
    for (ControlSequenceFunctionType csft : ControlSequenceFunctionType.values()) {
      for (ControlSequenceFunctionType otherCsft : ControlSequenceFunctionType.values()) {
        if (csft == otherCsft) {
          continue;
        }
        assertFalse(csft.toByte(true) == otherCsft.toByte(true));
        assertFalse(csft.toByte(false) == otherCsft.toByte(false));
      }

    }
  }

  @Test
  public void testConstructionOfControlSequenceFunctionType() throws AFPParserException {
    for (ControlSequenceFunctionType csft : ControlSequenceFunctionType.values()) {
      ControlSequenceFunctionType other = ControlSequenceFunctionType.valueOf((short) csft.toByte(false));
      assertTrue(csft == other);
    }
  }

}
