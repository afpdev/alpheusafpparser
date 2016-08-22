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

import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParser;
import com.mgz.afp.parser.AFPParserConfiguration;


/**
 * If an exception happens the parser builds a structured field of type {@link
 * StructuredFieldErrornouslyBuilt}.<br> <br> Note that {@link AFPParserConfiguration#setEscalateParsingErrors(boolean)}
 * has to be set to true for this behavior. Otherwise the parser escalates the occurring {@link
 * AFPParserException} to {@link AFPParser#error(AFPParserException)}.<br> <br> Note that the {@link
 * StructuredFieldErrornouslyBuilt#getData()} returns the gross payload, this is the SF's data,
 * <i>including</i> padding data. The {@link StructuredFieldErrornouslyBuilt#getPadding()} returns
 * null.<br>
 */
public class StructuredFieldErrornouslyBuilt extends StructuredFieldBaseData {
  Throwable causingException;

  /**
   * Returns the exception that happened when the parser tried to build this {@link
   * StructuredFieldErrornouslyBuilt}
   */
  public Throwable getCausingException() {
    return causingException;
  }

  public void setCausingException(Throwable causingException) {
    this.causingException = causingException;
  }
}
