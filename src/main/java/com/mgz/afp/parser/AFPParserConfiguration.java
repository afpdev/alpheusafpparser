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
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.security.DigestInputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The AFPParserConfiguration is used to configure the {@link AFPParser}, see {@link
 * AFPParser#AFPParser(AFPParserConfiguration)}.
 */
public class AFPParserConfiguration implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;
  /**
   * Indicates if the parser owns the input stream and should close it upon finishing.
   */
  protected boolean isParserOwnsInputStream;
  // Charset afpCharSet = Charset.forName("cp273"); // German.
  Charset afpCharSet = Charset.forName("cp500"); // CP500 is the default encoding.
  int bufferSize = 100 * 1024;
  InputStream inputStream;
  boolean isParseToStructuredFieldsBaseData;
  boolean isBuildShallow;
  boolean escalateParsingErrors = true;
  volatile boolean ptxDebug = false;
  File afpFile;
  private transient ByteBuffer byteBuffer;
  private transient AsynchronousFileChannel asyncFileChannel;
  private CPD_CodePageDescriptor currentCodePageDescriptor;
  private CPC_CodePageControl currentPageControl;
  private FNC_FontControl currentFontControl;
  private BDD_BarCodeDataDescriptor currentBarCodeDataDescriptor;
  private Map<Short, Charset> codedFontLocalIdToCharsetMap = new ConcurrentHashMap<>();

  /**
   * Default constructor for AFPParserConfiguration.
   */
  public AFPParserConfiguration() {
  }

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
   * as buffered input stream.
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

  /**
   * Returns the current font control.
   *
   * @return the current {@link FNC_FontControl}
   */
  public FNC_FontControl getCurrentFontControl() {
    return currentFontControl;
  }

  /**
   * Sets the current font control.
   *
   * @param fontControl the {@link FNC_FontControl} to set
   */
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
   * that provides only getter and setter for the structured fields's payload.
   * <p>
   * This mode is especially usefull when dealing with AFP data that isn't fully compliant to AFP
   * standards. In this mode, the parser is less restrictive, e.g. doesn't care if the length of the
   * structured field or individual attribute values are valid according to AFP specifications.
   *
   * @param isParseToStructuredFieldsBaseData true to parse to base data structured fields
   */
  public void setParseToStructuredFieldsBaseData(
      boolean isParseToStructuredFieldsBaseData) {
    this.isParseToStructuredFieldsBaseData = isParseToStructuredFieldsBaseData;
  }

  /**
   * Returns the current code page descriptor.
   *
   * @return the current {@link CPD_CodePageDescriptor}
   */
  public CPD_CodePageDescriptor getCurrentCPD_CodePageDescriptor() {
    return currentCodePageDescriptor;
  }

  /**
   * Returns the current code page control.
   *
   * @return the current {@link CPC_CodePageControl}
   */
  public CPC_CodePageControl getCurrentCodePageControl() {
    return currentPageControl;
  }

  /**
   * Returns true if the parser is building shallow {@link StructuredField}s. Shallow SFs consist
   * only of {@link StructuredFieldIntroducer}, the value of all other fields remain undefined until
   * {@link AFPParser#reload(StructuredField)} is called. Shallow SFs require considerably less
   * memory than fully realized SFs.
   *
   * @return true if building shallow objects
   */
  public boolean isBuildShallow() {
    return isBuildShallow;
  }

  /**
   * If set to true the parser is building shallow {@link StructuredField}s. See {@link
   * #isBuildShallow()}.
   *
   * @param isBuildShallow true to build shallow objects
   */
  public void setBuildShallow(boolean isBuildShallow) {
    this.isBuildShallow = isBuildShallow;
  }

  /**
   * Returns whether parsing errors should be escalated.
   *
   * @return true if parsing errors should be escalated
   */
  public boolean isEscalateParsingErrors() {
    return escalateParsingErrors;
  }

  /**
   * Returns whether PTX debug statistics should be collected.
   *
   * @return true if PTX debug is enabled
   */
  public boolean isPtxDebug() {
    return ptxDebug;
  }

  /**
   * Enables or disables PTX debug statistics collection.
   *
   * @param ptxDebug true to enable PTX debug
   */
  public void setPtxDebug(boolean ptxDebug) {
    this.ptxDebug = ptxDebug;
  }

  /**
   * If set to true (default) and a parsing error occurs, an {@link com.mgz.afp.exceptions.AFPParserException}
   * is thrown that carries the erroneous structured field as an
   * {@link com.mgz.afp.base.StructuredFieldErrornouslyBuilt}.
   * <p>
   * If set to false and a parsing error occurs, {@link AFPParser#parseNextSF()} returns a
   * {@link StructuredField} of type {@link com.mgz.afp.base.StructuredFieldErrornouslyBuilt} without throwing
   * an exception and continues parsing the rest of the AFP stream.
   *
   * @param escalateParsingErrors true if parsing errors should be escalated.
   */
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

  /**
   * Returns the current bar code data descriptor.
   *
   * @return the current {@link BDD_BarCodeDataDescriptor}
   */
  public BDD_BarCodeDataDescriptor getCurrentBarCodeDataDescriptor() {
    return currentBarCodeDataDescriptor;
  }

  /**
   * Sets the current bar code data descriptor.
   *
   * @param currentBarCodeDataDescriptor the {@link BDD_BarCodeDataDescriptor} to set
   */
  public void setCurrentBarCodeDataDescriptor(
      BDD_BarCodeDataDescriptor currentBarCodeDataDescriptor) {
    this.currentBarCodeDataDescriptor = currentBarCodeDataDescriptor;
  }

  /**
   * Returns the current code page descriptor.
   *
   * @return the current {@link CPD_CodePageDescriptor}
   */
  public CPD_CodePageDescriptor getCurrentCodePageDescriptor() {
    return currentCodePageDescriptor;
  }

  /**
   * Sets the current code page descriptor.
   *
   * @param currentCodePageDescriptor the {@link CPD_CodePageDescriptor} to set
   */
  public void setCurrentCodePageDescriptor(
      CPD_CodePageDescriptor currentCodePageDescriptor) {
    this.currentCodePageDescriptor = currentCodePageDescriptor;
  }

  /**
   * Returns the current code page control.
   *
   * @return the current {@link CPC_CodePageControl}
   */
  public CPC_CodePageControl getCurrentPageControl() {
    return currentPageControl;
  }

  /**
   * Sets the current code page control.
   *
   * @param currentPageControl the {@link CPC_CodePageControl} to set
   */
  public void setCurrentPageControl(CPC_CodePageControl currentPageControl) {
    this.currentPageControl = currentPageControl;
  }

  /**
   * Returns the charset for the given local identifier (LID).
   *
   * @param lid the local identifier
   * @return the {@link Charset} associated with the LID, or null if not found
   */
  public Charset getCharsetForLID(short lid) {
    return codedFontLocalIdToCharsetMap.get(lid);
  }

  /**
   * Adds a mapping between a local identifier (LID) and a charset.
   *
   * @param lid the local identifier
   * @param cs  the {@link Charset} to map
   */
  public void addCodedFontCharsetMapping(short lid, Charset cs) {
    codedFontLocalIdToCharsetMap.put(lid, cs);
  }

  /**
   * Returns the AFP file being parsed.
   *
   * @return the AFP {@link File}
   */
  public File getAFPFile() {
    return this.afpFile;
  }

  /**
   * Sets the AFP file to be parsed.
   *
   * @param afpFile the AFP {@link File} to set
   */
  public void setAFPFile(File afpFile) {
    this.afpFile = afpFile;
    this.byteBuffer = null;
    this.asyncFileChannel = null;
  }

  /**
   * Returns a {@link ByteBuffer} of the configured AFP file.
   *
   * @return the buffer, or null if no file is configured
   * @throws IOException if mapping the file fails
   */
  public ByteBuffer getByteBuffer() throws IOException {
    if (byteBuffer == null && afpFile != null) {
      try (RandomAccessFile raf = new RandomAccessFile(afpFile, "r");
           FileChannel fc = raf.getChannel()) {
        byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
      }
    }
    return byteBuffer;
  }

  /**
   * Sets the {@link ByteBuffer} to be parsed.
   *
   * @param buffer the {@link ByteBuffer} to set
   */
  public void setByteBuffer(ByteBuffer buffer) {
    this.byteBuffer = buffer;
  }

  /**
   * Returns an {@link AsynchronousFileChannel} of the configured AFP file.
   *
   * @return the asynchronous channel, or null if no file is configured
   * @throws IOException if opening the channel fails
   */
  public AsynchronousFileChannel getAsyncFileChannel() throws IOException {
    if (asyncFileChannel == null && afpFile != null) {
      asyncFileChannel = AsynchronousFileChannel.open(afpFile.toPath(), StandardOpenOption.READ);
    }
    return asyncFileChannel;
  }

  /**
   * Closes the {@link AsynchronousFileChannel} if it is open.
   *
   * @throws IOException if closing the channel fails
   */
  public void closeAsyncFileChannel() throws IOException {
    if (asyncFileChannel != null) {
      asyncFileChannel.close();
      asyncFileChannel = null;
    }
  }

  /**
   * Resets all preserved AFP objects that are needed by the parser for later reference to null.
   */
  public void resetCurrentAFPObjects() {
    currentBarCodeDataDescriptor = null;
    currentCodePageDescriptor = null;
    currentFontControl = null;
    currentPageControl = null;
    codedFontLocalIdToCharsetMap.clear();
  }
}
