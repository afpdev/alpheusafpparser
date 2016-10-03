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

import com.mgz.afp.base.IRepeatingGroup;
import com.mgz.afp.base.RepeatingGroupWithTriplets;
import com.mgz.afp.base.StructuredFieldBaseRepeatingGroups;
import com.mgz.afp.enums.AFPObjectType;
import com.mgz.afp.enums.IMutualExclusiveGroupedFlag;
import com.mgz.afp.enums.MutualExclusiveGroupedFlagHandler;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.UtilBinaryDecoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;

/**
 * MO:DCA, page 329. <br><br> The Preprocess Presentation Object structured field specifies
 * presentation parameters for a data object that has been mapped as a resource. These parameters
 * allow the presentation device to preprocess and cache the object so that it is in
 * presentation-ready format when it is included with a subsequent include structured field in the
 * document. Such preprocessing may involve a rasterization or RIP of the object, but is not limited
 * to that. The resource is identified with a file name, the identifier of a begin structured field
 * for the resource, or any other identifier associated with the resource. The referenced resource
 * and all required secondary resources must previously have been mapped with an MDR or an MPO in
 * the same environment group.
 */
public class PPO_PreprocessPresentationObject extends StructuredFieldBaseRepeatingGroups {

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);
    int pos = 0;
    while (pos < actualLength) {
      PPO_RepeatingGroup rg = new PPO_RepeatingGroup();
      rg.decodeAFP(sfData, offset + pos, actualLength - pos, config);
      addRepeatingGroup(rg);
      pos += rg.getRepeatingGroupLength();
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    for (IRepeatingGroup rg : repeatingGroups) {
      rg.writeAFP(baos, config);
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  public static class PPO_RepeatingGroup extends RepeatingGroupWithTriplets {
    AFPObjectType objectType;
    byte[] reserved3_4 = new byte[2];
    EnumSet<PPO_Flag> flags;
    int xOrigin;
    int yOrigin;

    @Override
    public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
      repeatingGroupLength = UtilBinaryDecoding.parseInt(sfData, offset, 2);
      objectType = AFPObjectType.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 1, 2));
      reserved3_4 = new byte[2];
      System.arraycopy(sfData, offset + 3, reserved3_4, 0, reserved3_4.length);
      flags = PPO_Flag.valueOf(UtilBinaryDecoding.parseShort(sfData, offset + 5, 1));
      xOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 6, 3);
      yOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 9, 3);
      triplets = TripletParser.parseTriplets(sfData, offset + 12, -1, config);
    }


    @Override
    public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
      os.write(UtilBinaryDecoding.intToByteArray(repeatingGroupLength, 2));
      os.write(objectType.toByte());
      os.write(reserved3_4);
      os.write(PPO_Flag.toByte(flags));
      os.write(UtilBinaryDecoding.intToByteArray(xOrigin, 3));
      os.write(UtilBinaryDecoding.intToByteArray(yOrigin, 3));
      for (Triplet t : triplets) {
        t.writeAFP(os, config);
      }
    }

    /**
     * Sets the given flag and unsets the mutually exclusive flags.
     *
     * @param flag {@link PPO_Flag} to set.
     */
    public void setFlag(PPO_Flag flag) {
      if (flags == null) {
        flags = EnumSet.noneOf(PPO_Flag.class);
      }
      PPO_Flag.setFlag(flags, flag);
    }

    public AFPObjectType getObjectType() {
      return objectType;
    }

    public void setObjectType(AFPObjectType objectType) {
      this.objectType = objectType;
    }

    public byte[] getReserved3_4() {
      return reserved3_4;
    }

    public void setReserved3_4(byte[] reserved3_4) {
      this.reserved3_4 = reserved3_4;
    }

    public EnumSet<PPO_Flag> getFlags() {
      return flags;
    }

    public void setFlags(EnumSet<PPO_Flag> flags) {
      this.flags = flags;
    }

    public int getxOrigin() {
      return xOrigin;
    }

    public void setxOrigin(int xOrigin) {
      this.xOrigin = xOrigin;
    }

    public int getyOrigin() {
      return yOrigin;
    }

    public void setyOrigin(int yOrigin) {
      this.yOrigin = yOrigin;
    }

    public enum PPO_Flag implements IMutualExclusiveGroupedFlag {
      ObjectOrientation_0Deg_DoNotPreprocess(0),
      ObjectOrientation_0Deg_Preprocess(0),
      ObjectOrientation_90Deg_DoNotPreprocess(1),
      ObjectOrientation_90Deg_Preprocess(1),
      ObjectOrientation_180Deg_DoNotPreprocess(2),
      ObjectOrientation_180Deg_Preprocess(2),
      ObjectOrientation_270Deg_DoNotPreprocess(3),
      ObjectOrientation_270Deg_Preprocess(3),
      PreprocessObjects_OnlySelected(4),
      PreprocessObjects_All(4);

      static MutualExclusiveGroupedFlagHandler<PPO_Flag> handler = new MutualExclusiveGroupedFlagHandler<PPO_Flag>();
      int group;

      PPO_Flag(int group) {
        this.group = group;
      }

      public static EnumSet<PPO_Flag> valueOf(short flagByte) {
        EnumSet<PPO_Flag> result = EnumSet.noneOf(PPO_Flag.class);
        if ((flagByte & 0x80) == 0) {
          result.add(ObjectOrientation_0Deg_DoNotPreprocess);
        } else {
          result.add(ObjectOrientation_0Deg_Preprocess);
        }
        if ((flagByte & 0x40) == 0) {
          result.add(ObjectOrientation_90Deg_DoNotPreprocess);
        } else {
          result.add(ObjectOrientation_90Deg_Preprocess);
        }
        if ((flagByte & 0x20) == 0) {
          result.add(ObjectOrientation_180Deg_DoNotPreprocess);
        } else {
          result.add(ObjectOrientation_180Deg_Preprocess);
        }
        if ((flagByte & 0x10) == 0) {
          result.add(ObjectOrientation_270Deg_DoNotPreprocess);
        } else {
          result.add(ObjectOrientation_270Deg_Preprocess);
        }
        if ((flagByte & 0x08) == 0) {
          result.add(PreprocessObjects_OnlySelected);
        } else {
          result.add(PreprocessObjects_All);
        }
        return result;
      }

      public static int toByte(EnumSet<PPO_Flag> flags) {
        int result = 0;
        if (flags.contains(ObjectOrientation_0Deg_Preprocess)) {
          result |= 0x80;
        }
        if (flags.contains(ObjectOrientation_90Deg_Preprocess)) {
          result |= 0x40;
        }
        if (flags.contains(ObjectOrientation_180Deg_Preprocess)) {
          result |= 0x20;
        }
        if (flags.contains(ObjectOrientation_270Deg_Preprocess)) {
          result |= 0x10;
        }
        if (flags.contains(PreprocessObjects_All)) {
          result |= 0x08;
        }
        return result;
      }

      public static void setFlag(EnumSet<PPO_Flag> flags, PPO_Flag flag) {
        handler.setFlag(flags, flag);
      }

      @Override
      public int getGroup() {
        return group;
      }
    }
  }
}
