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
package com.mgz.afp.modca;

import com.mgz.afp.base.IHasTriplets;
import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.exceptions.IAFPDecodeableWriteable;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>LLE_LinkLogicalElement</b> (MO:DCA, page 222)<br> A Link Logical Element structured field
 * specifies the linkage from a source document component to a target document component. The LLE
 * identifies the source and target and indicates the purpose of the linkage by specifying a link
 * type. The link source and link target may be in the same document component or in different
 * document components, and they need not be of the same document component type. The linkage may
 * involve a complete document component, or it may be restricted to a rectangular area on the
 * presentation space associated with the document component. The Link Logical Element structured
 * field can be embedded in the document that contains the link source, in the document that
 * contains the link target, in the document index for either document, or in any combination of
 * these structures. Link Logical Element parameters do not provide any presentation
 * specifications.
 */
public class LLE_LinkLogicalElement extends StructuredField {
  LLE_LinkType linkType;
  byte reserved1 = 0x00;
  List<LLE_RepeatingGroup> repeatingGroups;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    linkType = LLE_LinkType.valueOf(sfData[offset]);
    reserved1 = sfData[offset + 1];

    repeatingGroups = new ArrayList<LLE_LinkLogicalElement.LLE_RepeatingGroup>();
    int actualLength = getActualLength(sfData, offset, length);
    int pos = 2;
    while (pos < actualLength) {
      LLE_RepeatingGroup rg = new LLE_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
      repeatingGroups.add(rg);
      pos += rg.lengthOfRepeatingGroup;
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(linkType.toByte());
    baos.write(reserved1);
    for (LLE_RepeatingGroup rg : repeatingGroups) {
      rg.writeAFP(baos, config);
    }
    ;

    writeFullStructuredField(os, baos.toByteArray());
  }

  public LLE_LinkType getLinkType() {
    return linkType;
  }

  public void setLinkType(LLE_LinkType linkType) {
    this.linkType = linkType;
  }

  public byte getReserved1() {
    return reserved1;
  }

  public void setReserved1(byte reserved1) {
    this.reserved1 = reserved1;
  }

  public List<LLE_RepeatingGroup> getRepeatingGroups() {
    return repeatingGroups;
  }

  public void setRepeatingGroups(List<LLE_RepeatingGroup> repeatingGroups) {
    this.repeatingGroups = repeatingGroups;
  }

  public void addRepeatingGroup(LLE_RepeatingGroup repeatingGroup) {
    if (repeatingGroup == null) {
      return;
    }
    if (repeatingGroups == null) {
      repeatingGroups = new ArrayList<LLE_LinkLogicalElement.LLE_RepeatingGroup>();
    }
    repeatingGroups.add(repeatingGroup);
  }

  public void removeRepeatingGroup(LLE_RepeatingGroup repeatingGroup) {
    if (repeatingGroups == null) {
      return;
    } else {
      repeatingGroups.remove(repeatingGroup);
    }
  }

  /**
   * Specifies the purpose of the link.
   */
  public enum LLE_LinkType {
    NavigationLink,
    AnnotationLink,
    AppendLink;

    public static LLE_LinkType valueOf(byte linkTypeByte) {
      for (LLE_LinkType lt : values()) {
        if (lt.ordinal() == (linkTypeByte - 1)) {
          return lt;
        }
      }
      return null;
    }

    public int toByte() {
      return this.ordinal() + 1;
    }
  }

  public static class LLE_RepeatingGroup implements IAFPDecodeableWriteable, IHasTriplets {
    int lengthOfRepeatingGroup;
    LLE_RepeatingGroupFunction repeatingGroupFunction;
    List<Triplet> triplets;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      lengthOfRepeatingGroup = UtilBinaryDecoding.parseInt(sfData, offset, 2);
      repeatingGroupFunction = LLE_RepeatingGroupFunction.valueOf(sfData[offset + 2]);
      int actualLength = StructuredField.getActualLength(sfData, offset, length);
      if (actualLength > 3) {
        triplets = TripletParser.parseTriplets(sfData, offset + 3, actualLength - 3, config);
      } else {
        triplets = null;
      }
    }

    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      baos.write(repeatingGroupFunction.toByte());
      if (triplets != null) {
        for (Triplet t : triplets) {
          t.writeAFP(baos, config);
        }
      }
      lengthOfRepeatingGroup = baos.size() + 2;

      os.write(UtilBinaryDecoding.intToByteArray(lengthOfRepeatingGroup, 2));
      os.write(baos.toByteArray());
    }

    @Override
    public List<Triplet> getTriplets() {
      return triplets;
    }

    @Override
    public void setTriplets(List<Triplet> triplets) {
      this.triplets = triplets;
    }

    @Override
    public void addTriplet(Triplet triplet) {
      if (triplet == null) {
        return;
      }
      if (triplets == null) {
        triplets = new ArrayList<Triplet>();
      }
      triplets.add(triplet);
    }

    @Override
    public void removeTriplet(Triplet triplet) {
      if (triplets == null) {
        return;
      }
      triplets.remove(triplet);
    }

    public int getLengthOfRepeatingGroup() {
      return lengthOfRepeatingGroup;
    }

    public void setLengthOfRepeatingGroup(int lengthOfRepeatingGroup) {
      this.lengthOfRepeatingGroup = lengthOfRepeatingGroup;
    }

    public LLE_RepeatingGroupFunction getRepeatingGroupFunction() {
      return repeatingGroupFunction;
    }

    public void setRepeatingGroupFunction(
        LLE_RepeatingGroupFunction repeatingGroupFunction) {
      this.repeatingGroupFunction = repeatingGroupFunction;
    }

    public enum LLE_RepeatingGroupFunction {
      LinkAttributeSpecification,
      LinkSourceSpecification,
      LinkTargetSpecification;

      public static LLE_RepeatingGroupFunction valueOf(byte linkTypeByte) {
        for (LLE_RepeatingGroupFunction lt : values()) {
          if (lt.ordinal() == (linkTypeByte - 1)) {
            return lt;
          }
        }
        return null;
      }

      public int toByte() {
        return this.ordinal() + 1;
      }
    }

  }
}
