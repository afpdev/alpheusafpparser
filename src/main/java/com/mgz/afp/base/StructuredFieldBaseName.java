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
package com.mgz.afp.base;

import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilCharacterEncoding;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * The {@link Charset} used for de/encoding of the name is provided by {@link
 * AFPParserConfiguration#getAfpCharSet()}.
 */
public abstract class StructuredFieldBaseName extends StructuredField implements IHasName {
  @AFPField(maxSize = 8)
  private String name;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength >= 8) {
      name = new String(sfData, 0, 8, config.getAfpCharSet());
    } else {
      name = null;
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    if (name != null) writeFullStructuredField(os,
            UtilCharacterEncoding.stringToByteArray(name, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER)
    );
    else writeFullStructuredField(os, null);
  }

  @Override
  public final String getName() {
    return name;
  }

  @Override
  public final void setName(String name) {
    this.name = name;
  }

  @Override
  public void accept(final StructuredFieldVisitor visitor) {
    visitor.handle(this);
  }
}
