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
package com.mgz.afp.lineData;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Programming Guide and Line Data Reference (ha3l3r04.pdf), page 83<br> <br> The Conditional
 * Processing Control structured field defines tests to be performed on selected input records in
 * line data and specifies the actions to take based on the test results. This optional structured
 * field is selected with LND, RCD or XMD structured fields in the Page Definition. An LND, RCD or
 * XMD can have a unique CCP associated with it or it can reference a CCP that has already been
 * used. In either case, the CCP is referenced with the CCPID field of the LND, RCD or XMD. If a CCP
 * structured field is included in a Page Definition, it must appear before the Data Maps in the
 * Page Definition.
 */
public class CCP_ConditionalProcessingControl extends StructuredField {
  int ccpIdentifier;
  int nextCcpIdentifier;
  CCP_Flag flag;
  byte reserved5 = 0x00;
  int numberOfRepeatingGroups;
  int lengthOfRepeatingGroup;
  int lengthOfComparisonString;
  List<CCP_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    ccpIdentifier = UtilBinaryDecoding.parseInt(sfData, offset, 2);
    nextCcpIdentifier = UtilBinaryDecoding.parseInt(sfData, offset + 2, 2);
    flag = CCP_Flag.valueOf(sfData[offset + 4]);
    reserved5 = sfData[offset + 5];
    numberOfRepeatingGroups = UtilBinaryDecoding.parseInt(sfData, offset + 6, 2);
    lengthOfRepeatingGroup = UtilBinaryDecoding.parseInt(sfData, offset + 8, 2);
    lengthOfComparisonString = UtilBinaryDecoding.parseInt(sfData, offset + 10, 2);

    repeatingGroups = new ArrayList<CCP_RepeatingGroup>();
    int actualLength = getActualLength(sfData, offset, length);
    int pos = 12;
    while (pos < actualLength) {
      CCP_RepeatingGroup rg = new CCP_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, lengthOfRepeatingGroup, config);
      repeatingGroups.add(rg);

      pos += lengthOfRepeatingGroup;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(UtilBinaryDecoding.intToByteArray(ccpIdentifier, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(nextCcpIdentifier, 2));
    baos.write(flag.toByte());
    baos.write(reserved5);
    baos.write(UtilBinaryDecoding.intToByteArray(numberOfRepeatingGroups, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(lengthOfRepeatingGroup, 2));
    baos.write(UtilBinaryDecoding.intToByteArray(lengthOfComparisonString, 2));
    for (CCP_RepeatingGroup rg : repeatingGroups) rg.writeAFP(baos, config);

    writeFullStructuredField(os, baos.toByteArray());
  }

  public int getCcpIdentifier() {
    return ccpIdentifier;
  }

  public void setCcpIdentifier(int ccpIdentifier) {
    this.ccpIdentifier = ccpIdentifier;
  }

  public int getNextCcpIdentifier() {
    return nextCcpIdentifier;
  }

  public void setNextCcpIdentifier(int nextCcpIdentifier) {
    this.nextCcpIdentifier = nextCcpIdentifier;
  }

  public CCP_Flag getFlag() {
    return flag;
  }

  public void setFlag(CCP_Flag flag) {
    this.flag = flag;
  }

  public byte getReserved5() {
    return reserved5;
  }

  public void setReserved5(byte reserved5) {
    this.reserved5 = reserved5;
  }

  public int getNumberOfRepeatingGroups() {
    return numberOfRepeatingGroups;
  }

  public void setNumberOfRepeatingGroups(int numberOfRepeatingGroups) {
    this.numberOfRepeatingGroups = numberOfRepeatingGroups;
  }

  public int getLengthOfRepeatingGroup() {
    return lengthOfRepeatingGroup;
  }

  public void setLengthOfRepeatingGroup(int lengthOfRepeatingGroup) {
    this.lengthOfRepeatingGroup = lengthOfRepeatingGroup;
  }

  public int getLengthOfComparisonString() {
    return lengthOfComparisonString;
  }

  public void setLengthOfComparisonString(int lengthOfComparisonString) {
    this.lengthOfComparisonString = lengthOfComparisonString;
  }

  public List<CCP_RepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  public void setRepeatingGroups(List<CCP_RepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  public static enum CCP_Flag {
    BeforeSubpageActions(0x80),
    AfterSubpageActions(0x40),
    SpacingActions(0x20);
    int code;

    CCP_Flag(int code) {
      this.code = code;
    }

    public static CCP_Flag valueOf(byte flagByte) {
      for (CCP_Flag flag : values()) if (flag.code == flagByte) return flag;
      return null;
    }

    public int toByte() {
      return code;
    }
  }

  /**
   * Programming Guide and Line Data Reference (ha3l3r04.pdf), page 84<br> <br> Each repeating group
   * of the CCP contains action information. See Table 9 for the definitions of the CCP repeating
   * groups.
   */
  public static class CCP_RepeatingGroup implements IAFPDecodeableWriteable {
    CCP_TimingOfAction timingOfAction;
    CCP_MediumMapAction mediumMapAction;
    String mediumMapName;
    CCP_DataMapAction dataMapAction;
    String dataMapName;
    CCP_Comparison comparison;
    String comparisonString;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      int actualLength = getActualLength(sfData, offset, length);
      if (actualLength >= 1)
        timingOfAction = CCP_TimingOfAction.valueOf(UtilBinaryDecoding.parseInt(sfData, offset, 1));
      else timingOfAction = null;
      if (actualLength >= 2)
        mediumMapAction = CCP_MediumMapAction.valueOf(sfData[offset + 1]);
      else mediumMapAction = null;
      if (actualLength >= 10)
        mediumMapName = new String(sfData, offset + 2, 8, config.getAfpCharSet());
      else mediumMapName = null;
      if (actualLength >= 11) dataMapAction = CCP_DataMapAction.valueOf(sfData[offset + 10]);
      else dataMapAction = null;
      if (actualLength >= 19)
        dataMapName = new String(sfData, offset + 11, 8, config.getAfpCharSet());
      else dataMapName = null;
      if (actualLength >= 20) comparison = CCP_Comparison.valueOf(sfData[offset + 19]);
      else comparison = null;
      if (actualLength >= 21)
        comparisonString = new String(sfData, offset + 20, actualLength - 20, config.getAfpCharSet());
      else comparisonString = null;
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      if (timingOfAction != null) {
        os.write(timingOfAction.toByte());
        if (mediumMapAction != null) {
          os.write(mediumMapAction.toByte());
          if (mediumMapName != null) {
            os.write(mediumMapName.getBytes(config.getAfpCharSet()));
            if (dataMapAction != null) {
              os.write(dataMapAction.toByte());
              if (dataMapName != null) {
                os.write(dataMapName.getBytes(config.getAfpCharSet()));
                if (comparison != null) {
                  os.write(comparison.toByte());
                  if (comparisonString != null) {
                    os.write(comparisonString.getBytes(config.getAfpCharSet()));
                  }
                }
              }
            }
          }
        }
      }
    }

    public CCP_TimingOfAction getTimingOfAction() {
      return timingOfAction;
    }

    public void setTimingOfAction(CCP_TimingOfAction timingOfAction) {
      this.timingOfAction = timingOfAction;
    }

    public CCP_MediumMapAction getMediumMapAction() {
      return mediumMapAction;
    }

    public void setMediumMapAction(CCP_MediumMapAction mediumMapAction) {
      this.mediumMapAction = mediumMapAction;
    }

    public String getMediumMapName() {
      return mediumMapName;
    }

    public void setMediumMapName(String mediumMapName) {
      this.mediumMapName = mediumMapName;
    }

    public CCP_DataMapAction getDataMapAction() {
      return dataMapAction;
    }

    public void setDataMapAction(CCP_DataMapAction dataMapAction) {
      this.dataMapAction = dataMapAction;
    }

    public String getDataMapName() {
      return dataMapName;
    }

    public void setDataMapName(String dataMapName) {
      this.dataMapName = dataMapName;
    }

    public CCP_Comparison getComparison() {
      return comparison;
    }

    public void setComparison(CCP_Comparison comparison) {
      this.comparison = comparison;
    }

    public String getComparisonString() {
      return comparisonString;
    }

    public void setComparisonString(String comparisonString) {
      this.comparisonString = comparisonString;
    }

    public static enum CCP_TimingOfAction {
      Default_Immediately(0),
      Immediately(1),
      BeforeCurrentSubPage(2),
      AfterCurrentLine(129),
      AfterCurrentSubPage(130);

      int code;

      CCP_TimingOfAction(int code) {
        this.code = code;
      }

      public static CCP_TimingOfAction valueOf(int codeByte) {
        for (CCP_TimingOfAction toa : values()) if (toa.code == codeByte) return toa;
        return null;
      }

      public int toByte() {
        return code;
      }
    }

    public static enum CCP_MediumMapAction {
      Ignore,
      CurrentMediumMap_PageEject,
      InvokeNamedMediumMap,
      InvokeFirstMediumMap,
      InvokeNextMediumMap;

      public static CCP_MediumMapAction valueOf(byte codeByte) {
        for (CCP_MediumMapAction mma : values()) if (mma.ordinal() == codeByte) return mma;
        return null;
      }

      public int toByte() {
        return ordinal();
      }
    }

    public static enum CCP_DataMapAction {
      Ignore,
      CurrentDataMap_PageEject,
      InvokeNamedDataMap,
      InvokeFirstDataMap,
      InvokeNextDataMap;

      public static CCP_DataMapAction valueOf(byte codeByte) {
        for (CCP_DataMapAction dma : values()) if (dma.ordinal() == codeByte) return dma;
        return null;
      }

      public int toByte() {
        return ordinal();
      }
    }

    public static enum CCP_Comparison {
      AnyChange,
      EqualTo,
      LessThan,
      EqualToOrLessThan,
      GreaterThan,
      EqualToOrGreaterThan,
      NotEqual,
      TakeTheActionWithoutComparison;

      public static CCP_Comparison valueOf(byte codeByte) {
        for (CCP_Comparison comp : values()) if (comp.ordinal() == codeByte) return comp;
        return null;
      }

      public int toByte() {
        return ordinal();
      }
    }
  }


}
