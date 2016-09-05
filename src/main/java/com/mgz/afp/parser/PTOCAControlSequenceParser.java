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
package com.mgz.afp.parser;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.ControlSequenceIntroducer;

import java.util.ArrayList;
import java.util.List;

public class PTOCAControlSequenceParser {
  public static List<PTOCAControlSequence> parseControlSequences(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {

    List<PTOCAControlSequence> controlSequences = new ArrayList<PTOCAControlSequence>();

    int actualLength = StructuredField.getActualLength(sfData, offset, length);
    int pos = 0;
    boolean isChained = false;
    while (pos < actualLength) {
      ControlSequenceIntroducer csi = ControlSequenceIntroducer.parseCSI(isChained, sfData, offset + pos, -1, config);

      PTOCAControlSequence cs = createControlSequenceInstance(csi);
      // Move pos to begin of CS payload.
      if (isChained) pos += 2;
      else pos += 4;

      cs.decodeAFP(sfData, offset + pos, csi.getLength() - 2, config);
      isChained = cs.getCsi().isChained();

      controlSequences.add(cs);
      pos += csi.getLength() - 2;
    }

    return controlSequences;
  }

  public static final PTOCAControlSequence createControlSequenceInstance(ControlSequenceIntroducer csi) throws AFPParserException {
    PTOCAControlSequence cs = null;
    String className = null;
    try {
      className = com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.class.getName() + "$" + csi.getControlSequenceFunctionType().name();
      Class<?> clazz = Class.forName(className);
      cs = (PTOCAControlSequence) clazz.newInstance();
    } catch (Throwable cnfex) {
      throw new AFPParserException(PTOCAControlSequence.class.getSimpleName() + ": failed to instantiate control sequence class '" + className + "'.");
    }

    if (cs == null) cs = new com.mgz.afp.ptoca.controlSequence.Undefined();
    cs.setCsi(csi);

    return cs;
  }

}
