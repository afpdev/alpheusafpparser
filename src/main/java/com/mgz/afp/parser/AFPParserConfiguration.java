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
import com.mgz.afp.base.StructuredFieldBaseData;
import com.mgz.afp.base.StructuredFieldIntroducer;
import com.mgz.afp.bcoca.BDD_BarCodeDataDescriptor;
import com.mgz.afp.foca.CPC_CodePageControl;
import com.mgz.afp.foca.CPD_CodePageDescriptor;
import com.mgz.afp.foca.FNC_FontControl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.DigestInputStream;

/**
 * The AFPParserConfiguration is used to configure the {@link AFPParser}, see {@link
 * AFPParser#AFPParser(AFPParserConfiguration)}.
 */
public class AFPParserConfiguration implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  protected boolean isParserOwnsInputStream;
  Charset afpCharSet = Charset.forName("cp273");
  int bufferSize = 100 * 1024;
  InputStream inputStream;
  boolean isParseToStructuredFieldsBaseData;
  boolean isBuildShallow;
  boolean escalateParsingErrors = true;
  File afpFile;
  private CPD_CodePageDescriptor currentCodePageDescriptor;
  private CPC_CodePageControl currentPageControl;
  private FNC_FontControl currentFontControl;
  private BDD_BarCodeDataDescriptor currentBarCodeDataDescriptor;

  /**
   * Returns the {@link Charset} used to decode text contained in the AFP data stream (e.g.
   * PTX,NOP,TLE).
   *
   * @return {@link Charset} used in the AFP data stream.
   */
  public Charset getAfpCharSet() {
    return afpCharSet;
  }

  /**
   * Sets the {@link Charset} used to decode text contained in the AFP data stream (e.g.
   * PTX,NOP,TLE).
   *
   * @param afpCharSet {@link Charset} used in the AFP data stream.
   */
  public void setAfpCharSet(Charset afpCharSet) {
    this.afpCharSet = afpCharSet;
  }

  /**
   * Returns the size of input buffer used by the parser.
   *
   * @return size of input file buffer used by the parser.
   */
  public int getBufferSize() {
    return bufferSize;
  }

  /**
   * Sets the size of input buffer used by the parser. Has influence on performance. Default is
   * 500KB. Minimum is 100KB. A higher value can result in higher performance of the parser.
   *
   * @param bufferSize size of input file buffer used by the parser.
   */
  public void setBufferSize(int bufferSize) {
    this.bufferSize = bufferSize;
  }

  /**
   * Returns the {@link InputStream} from where the parser reads the AFP data stream. If no input
   * stream is set this method tries to open the configured AFP file (see {@link #setAFPFile(File)})
   * as buffered input stream.<br>
   *
   * @return the {@link InputStream} from where the parser reads the AFP data stream.
   * @throws IOException if the input stream is not set and the opening of the configured AFP file
   *                     could not be opened.
   */
  public InputStream getInputStream() throws IOException {
    if (inputStream == null && afpFile != null) {
      inputStream = new BufferedInputStream(new FileInputStream(afpFile), this.bufferSize);
      isParserOwnsInputStream = true;
    } else if (inputStream != null && !(inputStream instanceof BufferedInputStream || inputStream instanceof DigestInputStream) && bufferSize > 0) {
      inputStream = new BufferedInputStream(inputStream, this.bufferSize);
    }
    return inputStream;
  }

  /**
   * Sets the {@link InputStream} from where the parser reads the AFP data stream.
   *
   * @param inputStream the {@link InputStream} from where the parser reads the AFP data stream.
   */
  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public FNC_FontControl getCurrentFontControl() {
    return currentFontControl;
  }

  public void setCurrentFontControl(FNC_FontControl fontControl) {
    this.currentFontControl = fontControl;
  }

  /**
   * Returns true if the parser decodes structured fields of type {@link StructuredFieldBaseData}
   * and the payload of the structured field is seen as blackbox and not further processed by the
   * parser.
   *
   * @return true if the parser generates {@link StructuredFieldBaseData}.
   */
  public boolean isParseToStructuredFieldsBaseData() {
    return isParseToStructuredFieldsBaseData;
  }

  /**
   * If set to true the parser produces structured fields of type {@link StructuredFieldBaseData}.
   * {@link StructuredFieldBaseData} have a full blown {@link StructuredFieldIntroducer} but beside
   * that provides only getter and setter for the structured fields's payload.<br> <br> This mode is
   * especially usefull when dealing with AFP data that isn't fully compliant to AFP standards. In
   * this mode, the parser is less restrictive, e.g. doesn't care if the length of the structured
   * field or individual attribute values are valid according to AFP specifications.
   */
  public void setParseToStructuredFieldsBaseData(
          boolean isParseToStructuredFieldsBaseData) {
    this.isParseToStructuredFieldsBaseData = isParseToStructuredFieldsBaseData;
  }

  public CPD_CodePageDescriptor getCurrentCPD_CodePageDescriptor() {
    return currentCodePageDescriptor;
  }

  public CPC_CodePageControl getCurrentCodePageControl() {
    return currentPageControl;
  }

  /**
   * Returns true if the parser is building shallow {@link StructuredField}s. Shallow SFs consist
   * only of {@link StructuredFieldIntroducer}, the value of all other fields remain undefined until
   * {@link AFPParser#reload(StructuredField)} is called. Shallow SFs require considerably less
   * memory than fully realized SFs.
   */
  public boolean isBuildShallow() {
    return isBuildShallow;
  }

  /**
   * If set to true the parser is building shallow {@link StructuredField}s. See {@link
   * #isBuildShallow()}.
   */
  public void setBuildShallow(boolean isBuildShallow) {
    this.isBuildShallow = isBuildShallow;
  }

  public boolean isEscalateParsingErrors() {
    return escalateParsingErrors;
  }

  public void setEscalateParsingErrors(boolean escalateParsingErrors) {
    this.escalateParsingErrors = escalateParsingErrors;
  }

  @Override
  public AFPParserConfiguration clone() {
    try {
      return (AFPParserConfiguration) super.clone();
    } catch (CloneNotSupportedException e) {
      return null;
    }
  }

  public BDD_BarCodeDataDescriptor getCurrentBarCodeDataDescriptor() {
    return currentBarCodeDataDescriptor;
  }

  public void setCurrentBarCodeDataDescriptor(
          BDD_BarCodeDataDescriptor currentBarCodeDataDescriptor) {
    this.currentBarCodeDataDescriptor = currentBarCodeDataDescriptor;
  }

  public CPD_CodePageDescriptor getCurrentCodePageDescriptor() {
    return currentCodePageDescriptor;
  }

  public void setCurrentCodePageDescriptor(
          CPD_CodePageDescriptor currentCodePageDescriptor) {
    this.currentCodePageDescriptor = currentCodePageDescriptor;
  }

  public CPC_CodePageControl getCurrentPageControl() {
    return currentPageControl;
  }

  public void setCurrentPageControl(CPC_CodePageControl currentPageControl) {
    this.currentPageControl = currentPageControl;
  }

  public File getAFPFile() {
    return this.afpFile;
  }

  public void setAFPFile(File afpFile) {
    this.afpFile = afpFile;
  }

  /**
   * Resets all preserved AFP objects that are needed by the parser for later reference to null.
   */
  public void resetCurrentAFPObjects() {
    currentBarCodeDataDescriptor = null;
    currentCodePageDescriptor = null;
    currentFontControl = null;
    currentPageControl = null;
  }
}
