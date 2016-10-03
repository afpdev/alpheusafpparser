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
import com.mgz.afp.base.StructuredFieldErrornouslyBuilt;
import com.mgz.afp.base.StructuredFieldIntroducer;
import com.mgz.afp.bcoca.BDD_BarCodeDataDescriptor;
import com.mgz.afp.enums.SFFlag;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.foca.CPC_CodePageControl;
import com.mgz.afp.foca.CPD_CodePageDescriptor;
import com.mgz.afp.foca.FNC_FontControl;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;

import java.io.IOException;
import java.io.InputStream;

public class AFPParser {

  private static String afpPackagePrefix = "com.mgz.afp.";
  private static String[] afpPackages = {
      afpPackagePrefix + "modca.",
      afpPackagePrefix + "ptoca.",
      afpPackagePrefix + "foca.",
      afpPackagePrefix + "ioca.",
      afpPackagePrefix + "goca.",
      afpPackagePrefix + "bcoca.",
      afpPackagePrefix + "lineData.",
      afpPackagePrefix + "modca_L."
  };
  AFPParserConfiguration parserConf;
  long nrOfBytesRead;
  long nrOfSFBuilt;
  long nrOfErrSFBuilt;


  /**
   * Constructor.
   *
   * @param parserConfiguration see {@link AFPParserConfiguration}
   */
  public AFPParser(AFPParserConfiguration parserConfiguration) {
    nrOfBytesRead = 0;
    nrOfSFBuilt = 0;
    nrOfErrSFBuilt = 0;
    parserConf = parserConfiguration;
  }

  public static StructuredField createSFInstance(StructuredFieldIntroducer sfi) {
    StructuredField sf = null;
    for (String afpPackage : afpPackages) {
      Class<?> clazz;
      try {
        String className = afpPackage + sfi.getSFTypeID().name();
        clazz = Class.forName(className);
        sf = (StructuredField) clazz.newInstance();
      } catch (Exception cnfex) {
        continue;
      }
    }

    if (sf == null) {
      sf = new com.mgz.afp.base.Undefined();
    }
    sf.setStructuredFieldIntroducer(sfi);
    return sf;
  }

  public static void reload(StructuredField sf) throws AFPParserException {
    if (sf == null || sf.getStructuredFieldIntroducer() == null) {
      return;
    }

    StructuredFieldIntroducer sfi = sf.getStructuredFieldIntroducer();
    AFPParserConfiguration conf = sfi.getActualConfig();
    if (conf.getAFPFile() == null) {
      throw new AFPParserException("The file from whitch the structured field has been loaded is unknown.");
    }

    synchronized (conf) {
      InputStream is = null;
      try {
        conf.setInputStream(null);
        is = conf.getInputStream();
        long lenSFI = sfi.getFileOffset() + 1 + sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();
        if (is.skip(lenSFI) < lenSFI) {
          throw new AFPParserException("Failed to skip over SF Introducer.");
        }

        int lenOfGrossPayload = sfi.getSFLength() - sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();
        byte[] grossPayload = new byte[lenOfGrossPayload];
        byte[] sfData, padding;


        // Determine payload.
        if (lenOfGrossPayload > 0) {

          int read = 0;
          while (read < lenOfGrossPayload) {
            int len = is.read(grossPayload, read, lenOfGrossPayload - read);
            if (len == -1) {
              throw new AFPParserException("Reached end of file before end of structured field.");
            } else {
              read += len;
            }
          }

          // Determine net payload.
          if (sfi.isFlagSet(SFFlag.isPadded)) {
            int lenOfPadding = grossPayload[grossPayload.length - 1];
            if (lenOfPadding == 0) {
              lenOfPadding = UtilBinaryDecoding.parseInt(grossPayload, grossPayload.length - 3, 2);
            }

            int lenOfSFData = lenOfGrossPayload - lenOfPadding;

            sfData = new byte[lenOfSFData];
            padding = new byte[lenOfPadding];

            System.arraycopy(grossPayload, 0, sfData, 0, lenOfSFData);
            System.arraycopy(grossPayload, lenOfSFData, padding, 0, lenOfPadding);

          } else {
            sfData = grossPayload;
            padding = null;
          }

          sf.setPadding(padding);
          sf.decodeAFP(sfData, 0, -1, conf);
        }

      } catch (Throwable th) {
        if (th instanceof AFPParserException) {
          throw (AFPParserException) th;
        } else {
          throw new AFPParserException("Reload failed.", th);
        }
      } finally {
        if (is != null) {
          try {
            is.close();
            conf.setInputStream(null);
          } catch (IOException e) {
            throw new AFPParserException("Failed to close input stream.", e);
          }
        }
      }
    }
  }

  /**
   * Call this method to perform the parsing. Blocks until the parsing finished, either by end of
   * input stream or the occurrence of an {@link AFPParserException}.
   */
  public final StructuredField parseNextSF() throws AFPParserException {
    StructuredFieldIntroducer sfi = null;
    StructuredFieldErrornouslyBuilt errSf = null;
    try {
      InputStream is = parserConf.getInputStream();

      int tmp;
      byte[] sfData;
      byte[] padding;
      do {
        tmp = is.read();
        if (tmp != -1) {
          nrOfBytesRead++;
        }
      }
      while (tmp != Constants.AFPBeginByte_0xA5 && tmp != -1); // Move to the begin of next SF, or EOF.

      if (tmp != -1) {
        sfi = StructuredFieldIntroducer.parse(is);
        sfi.setFileOffset(nrOfBytesRead - 1);

        StructuredField sf;
        if (parserConf.isParseToStructuredFieldsBaseData) {
          sf = new StructuredFieldBaseData();
          sf.setStructuredFieldIntroducer(sfi);
        } else {
          sf = createSFInstance(sfi);
        }

        int lenOfGrossPayload = sfi.getSFLength() - sfi.getLengthOfStructuredFieldIntroducerIncludingExtension();

        if (parserConf.isBuildShallow()) {
          AFPParserConfiguration actualConf = parserConf.clone();
          actualConf.setInputStream(null);
          sfi.setActualConfig(actualConf);
          if (is.skip(lenOfGrossPayload) < lenOfGrossPayload) {
            throw new AFPParserException("Failed to skip payload while building shallow objects.");
          }

        } else {

          byte[] grossPayload = new byte[lenOfGrossPayload];

          try {
            // Determine payload.
            if (lenOfGrossPayload > 0) {

              int read = 0;
              while (read < lenOfGrossPayload) {
                int len = is.read(grossPayload, read, lenOfGrossPayload - read);
                if (len == -1) {
                  throw new AFPParserException("Reached end of file before end of structured field.");
                } else {
                  read += len;
                }
              }

              // Determine net payload.
              if (sfi.isFlagSet(SFFlag.isPadded)) {
                int lenOfPadding = grossPayload[grossPayload.length - 1];
                if (lenOfPadding == 0) {
                  lenOfPadding = UtilBinaryDecoding.parseInt(grossPayload, grossPayload.length - 3, 2);
                }

                int lenOfSFData = lenOfGrossPayload - lenOfPadding;

                sfData = new byte[lenOfSFData];
                padding = new byte[lenOfPadding];

                System.arraycopy(grossPayload, 0, sfData, 0, lenOfSFData);
                System.arraycopy(grossPayload, lenOfSFData, padding, 0, lenOfPadding);

              } else {
                sfData = grossPayload;
                padding = null;
              }

              sf.setPadding(padding);
              sf.decodeAFP(sfData, 0, -1, parserConf);
            }
          } catch (Throwable th) {
            sf = errSf = new StructuredFieldErrornouslyBuilt();
            errSf.setCausingException(th);
            errSf.setStructuredFieldIntroducer(sfi);
            errSf.setData(grossPayload);
            if (parserConf.isEscalateParsingErrors()) {
              throw th;
            }
          }

        }
        if (sf != null) {

          // Preserve certain SFs which maybe referenced by later SFs.
          if (sf instanceof FNC_FontControl) {
            parserConf.setCurrentFontControl((FNC_FontControl) sf);
          } else if (sf instanceof CPD_CodePageDescriptor) {
            parserConf.setCurrentCodePageDescriptor((CPD_CodePageDescriptor) sf);
          } else if (sf instanceof CPC_CodePageControl) {
            parserConf.setCurrentPageControl((CPC_CodePageControl) sf);
          } else if (sf instanceof BDD_BarCodeDataDescriptor) {
            parserConf.setCurrentBarCodeDataDescriptor((BDD_BarCodeDataDescriptor) sf);
          }

          nrOfBytesRead += sf.getStructuredFieldIntroducer().getSFLength();
          nrOfSFBuilt++;
        }

        return sf;

      } else {
        return null;
      }

    } catch (Throwable e) {

      if (errSf == null) {
        errSf = new StructuredFieldErrornouslyBuilt();
        errSf.setStructuredFieldIntroducer(sfi);
      }

      nrOfBytesRead += errSf.getStructuredFieldIntroducer().getSFLength();
      nrOfSFBuilt++;
      nrOfErrSFBuilt++;

      // Call error() which may or may not re-throw the given exception
      if (e instanceof AFPParserException) {
        ((AFPParserException) e).setErrornouslyBuiltStructuredField(errSf);
        error((AFPParserException) e);
      } else {
        AFPParserException afpex = new AFPParserException("An exception occured when parsing structured field at file index position 0x" + Long.toHexString(nrOfBytesRead) + ".", e);
        afpex.setErrornouslyBuiltStructuredField(errSf);
        error(afpex);
      }

      return errSf;
    }
  }

  /**
   * This method is called by the parser if an error condition is reached by the parser, e.g. the
   * AFP stream has errors. This method just throws the {@link AFPParserException}. Override this
   * method in order to handle/ignore exception by your application and continue parsing.
   */
  public void error(AFPParserException afpExc) throws AFPParserException {
    throw afpExc;
  }

  public long getCountReadByte() {
    return nrOfBytesRead;
  }

  public void quitParsing() throws AFPParserException {
    parserConf.resetCurrentAFPObjects();

    if (parserConf.isParserOwnsInputStream && parserConf.inputStream != null) {
      try {
        parserConf.inputStream.close();
      } catch (IOException e) {
        throw new AFPParserException("Failed to close input stream.", e);
      }
    }
  }

  /**
   * Returns the total number of structured fields that has been built so far.
   */
  public long getNrOfSFBuilt() {
    return nrOfSFBuilt;
  }

  /**
   * Returns the number of structured fields that has been built with errrors so far.
   */
  public long getNrOfSFBuiltWithErrors() {
    return nrOfErrSFBuilt;
  }
}
