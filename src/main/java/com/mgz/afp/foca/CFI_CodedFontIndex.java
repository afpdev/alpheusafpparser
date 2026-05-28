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

package com.mgz.afp.foca;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>CFI – D38C8A – Coded Font Index</b><br> The Coded Font Index (CFI) identifies one or more Font
 * Character Set and Code Page object pairs, and associated size information for outline font
 * scaling.<br> <br> CFI structured field names the font character sets and code pages for the font.
 * The structured field contains a set of parameters, defined as a repeating group. The length of
 * the repeating group is defined in the Coded Font Control structured field. The number of
 * repeating groups in the structured field can be determine by dividing the length of the CFI
 * structured field (minus the length of the structured field introducer) by the length of the CFI
 * repeating group. The repeating groups are sorted in ascending order based on the section
 * identifier.<br> <br> <b>Single-Byte and Double-byte Outline</b><br> There is only one section for
 * a single-byte font, or a double-byte outline font; therefore, there is only one repeating
 * group.<br> <br> <b>Double-Byte Raster Coded Fonts</b><br> There can be 190 sections (X'41' to
 * X'FE'). Each section requires its own repeating group. The repeating groups are sorted in
 * ascending order based on the section identifier.<br> <br> The data for the CFI structured field
 * consists of a series of repeating groups.
 */
public class CFI_CodedFontIndex extends StructuredField {
  public static final int CFIRepeatingGroupLength = CFC_CodedFontControl.CFI_REPEATING_GROUP_LENGTH_DEFAULT;

  @AFPField
  private List<CFIRepeatingGroup> cfiRepeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);

    cfiRepeatingGroups = new ArrayList<>();
    var pos = 0;
    while (pos + CFIRepeatingGroupLength <= actualLength) {
      var cfiRepeatingGroup = new CFIRepeatingGroup();
      cfiRepeatingGroup.decodeAFP(sfData, offset + pos, CFIRepeatingGroupLength, config);

      cfiRepeatingGroups.add(cfiRepeatingGroup);

      pos += CFIRepeatingGroupLength;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    var baos = new ByteArrayOutputStream();

    if (cfiRepeatingGroups != null) {
      for (var cfiRG : cfiRepeatingGroups) {
        cfiRG.writeAFP(baos, config);
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public List<CFIRepeatingGroup> getCfiRepeatingGroups() {
    return cfiRepeatingGroups;
  }

  public void setCfiRepeatingGroups(List<CFIRepeatingGroup> cfiRepeatingGroups) {
    this.cfiRepeatingGroups = cfiRepeatingGroups;
  }

  public void addCFIRepeatingGroup(CFIRepeatingGroup CFIrg) {
    if (CFIrg == null) {
      return;
    }
    if (this.cfiRepeatingGroups == null) {
      this.cfiRepeatingGroups = new ArrayList<>();
    }
    this.cfiRepeatingGroups.add(CFIrg);
  }

  public void removeCFIRepeatingGroup(CFIRepeatingGroup CFIrg) {
    if (this.cfiRepeatingGroups == null) {
      return;
    } else {
      this.cfiRepeatingGroups.remove(CFIrg);
    }
  }

  /**
   * CFI Repeating Group.
   */
  @XmlRootElement
  public static class CFIRepeatingGroup implements IAFPDecodeableWriteable {
    /**
     * Font Character Set Name.
     */
    @AFPField
    private String fcsName;
    /**
     * Code Page Name.
     */
    @AFPField
    private String cpName;
    /**
     * Specified Vertical Font Size,  in 20ths of a point (1440ths of an inch).
     */
    @AFPField
    private int svSize;
    /**
     * Specified Horizontal Scale Factor, in 20ths of a point (1440ths of an inch).
     */
    @AFPField
    private int shScale;
    /**
     * Section Number:<br><b>0x00</b> Single-byte<br><b>0x41 – 0xFE</b> Double-byte
     * Raster<br><b>0xFF</b> reserved.
     */
    @AFPField
    private short section;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      checkDataLength(sfData, offset, length, CFIRepeatingGroupLength);

      fcsName = new String(sfData, offset, 8, config.getAfpCharSet()).trim();
      cpName = new String(sfData, offset + 8, 8, config.getAfpCharSet()).trim();
      svSize = UtilBinaryDecoding.parseInt(sfData, offset + 16, 2);
      shScale = UtilBinaryDecoding.parseInt(sfData, offset + 18, 2);
      section = (short) (sfData[offset + 24] & 0xFF);
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilCharacterEncoding.stringToByteArray(fcsName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
      os.write(UtilCharacterEncoding.stringToByteArray(cpName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
      os.write(UtilBinaryDecoding.intToByteArray(svSize, 2));
      os.write(UtilBinaryDecoding.intToByteArray(shScale, 2));
      os.write(new byte[4]); // reserved.
      os.write(UtilBinaryDecoding.shortToByteArray(section, 1));
    }

    public String getFcsName() {
      return fcsName;
    }

    public void setFcsName(String fcsName) {
      this.fcsName = fcsName;
    }

    public String getCpName() {
      return cpName;
    }

    public void setCpName(String cpName) {
      this.cpName = cpName;
    }

    public int getSvSize() {
      return svSize;
    }

    public void setSvSize(int svSize) {
      this.svSize = svSize;
    }

    public int getShScale() {
      return shScale;
    }

    public void setShScale(int shScale) {
      this.shScale = shScale;
    }

    public short getSection() {
      return section;
    }

    public void setSection(short section) {
      this.section = section;
    }
  }
}
