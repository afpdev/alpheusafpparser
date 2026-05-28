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

package com.mgz.afp.exceptions;

import com.mgz.afp.base.StructuredField;

/**
 * Exception thrown when the AFP parser encounters an error.
 */
public class AFPParserException extends AFPException {
  private static final long serialVersionUID = 1L;

  /**
   * The structured field that was being built when the error occurred.
   */
  StructuredField errornouslyBuiltStructuredField;

  /**
   * Constructor with message and cause.
   *
   * @param msg the detail message
   * @param e   the cause
   */
  public AFPParserException(String msg, Throwable e) {
    super(msg, e);
  }

  /**
   * Constructor with message.
   *
   * @param msg the detail message
   */
  public AFPParserException(String msg) {
    super(msg);
  }

  /**
   * Returns the structured field that was being built when the error occurred.
   *
   * @return the erroneously built structured field
   */
  public StructuredField getErrornouslyBuiltStructuredField() {
    return errornouslyBuiltStructuredField;
  }

  /**
   * Sets the structured field that was being built when the error occurred.
   *
   * @param errSf the erroneously built structured field
   */
  public void setErrornouslyBuiltStructuredField(StructuredField errSf) {
    errornouslyBuiltStructuredField = errSf;
  }
}
