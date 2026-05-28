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

package com.mgz.afp.ptoca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.PTOCAControlSequenceParser;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PTX_PresentationTextData extends StructuredField {
  @AFPField
  @XmlTransient
  List<PTOCAControlSequence> controlSequences;

  @XmlAnyElement(lax = true)
  public List<PTOCAControlSequence> getControlSequencesXml() {
    return controlSequences;
  }

  volatile byte[] originalPayload;
  volatile Throwable controlSequenceException;

  @Override
  public void reset() {
    super.reset();
    controlSequences = null;
    originalPayload = null;
    controlSequenceException = null;
  }

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    long startTime = config.isPtxDebug() ? System.nanoTime() : 0;
    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 0) {
      originalPayload = new byte[actualLength];
      System.arraycopy(sfData, offset, originalPayload, 0, actualLength);
      controlSequences = PTOCAControlSequenceParser.parseControlSequences(sfData, offset, actualLength, config);
    } else {
      originalPayload = null;
      controlSequences = null;
    }

    if (config.isPtxDebug()) {
      long duration = System.nanoTime() - startTime;
      com.mgz.util.PTXPerformanceMonitor.recordPtxParse(duration, actualLength, controlSequences != null ? controlSequences.size() : 0);
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    byte[] actualPayload = null;
    if (controlSequences != null) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      for (int i = 0; i < controlSequences.size(); i++) {
        PTOCAControlSequence cs = controlSequences.get(i);
        ByteArrayOutputStream csBaos = new ByteArrayOutputStream();
        cs.writeAFP(csBaos, config);
        baos.write(cs.getCsi().toBytes());
        baos.write(csBaos.toByteArray());
      }
      actualPayload = baos.toByteArray();
    } else if (originalPayload != null) {
      actualPayload = originalPayload;
    }
    writeFullStructuredField(os, actualPayload);
  }

  public List<PTOCAControlSequence> getControlSequences() {
    return controlSequences;
  }

  public void setControlSequences(List<PTOCAControlSequence> controlSequences) {
    this.controlSequences = controlSequences;
  }

  public void addControlSequence(PTOCAControlSequence cs) {
    if (cs == null) {
      return;
    }
    if (controlSequences == null) {
      controlSequences = new ArrayList<PTOCAControlSequence>();
    }
    controlSequences.add(cs);
  }

  public void removeControlSequence(PTOCAControlSequence cs) {
    if (controlSequences == null) {
      return;
    }
    controlSequences.remove(cs);
  }

  @Override
  public void release() {
    if (controlSequences != null) {
      for (PTOCAControlSequence sequence : controlSequences) {
        sequence.release();
      }
      controlSequences = null;
    }
    super.release();
  }
}
