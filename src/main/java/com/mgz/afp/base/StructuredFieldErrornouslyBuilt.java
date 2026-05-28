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

import com.mgz.afp.enums.SFTypeID;
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
  private long fileOffset = -1;
  private SFTypeID typeId;
  private byte[] rawIntroducer;

  /**
   * Returns the exception that happened when the parser tried to build this {@link
   * StructuredFieldErrornouslyBuilt}.
   */
  public Throwable getCausingException() {
    return causingException;
  }

  public void setCausingException(Throwable causingException) {
    this.causingException = causingException;
  }

  public long getFileOffset() {
    return fileOffset;
  }

  public void setFileOffset(long fileOffset) {
    this.fileOffset = fileOffset;
  }

  public SFTypeID getTypeId() {
    return typeId;
  }

  public void setTypeId(SFTypeID typeId) {
    this.typeId = typeId;
  }

  public byte[] getRawIntroducer() {
    return rawIntroducer;
  }

  public void setRawIntroducer(byte[] rawIntroducer) {
    this.rawIntroducer = rawIntroducer;
  }

  /**
   * Returns a detailed error message for this erroneous structured field.
   *
   * @return the error message
   */
  public String getErrorMessage() {
    StringBuilder sb = new StringBuilder();
    sb.append("Error at file offset 0x").append(Long.toHexString(fileOffset).toUpperCase());
    if (typeId != null) {
      sb.append(" (").append(typeId.name()).append(")");
    } else if (rawIntroducer != null && rawIntroducer.length >= 5) {
        // Try to show the hex type if ID is unknown.
        // Type ID (Class, Type, Category) is at indices 2, 3, 4 of the 8-byte SFI.
        sb.append(" (Unknown Type: ")
          .append(String.format("%02X%02X%02X", rawIntroducer[2], rawIntroducer[3], rawIntroducer[4]))
          .append(")");
    }
    sb.append(": ");
    if (causingException != null) {
      sb.append(causingException.getMessage() != null ? causingException.getMessage() : causingException.getClass().getSimpleName());
    } else {
      sb.append("Unknown error");
    }
    return sb.toString();
  }
}
