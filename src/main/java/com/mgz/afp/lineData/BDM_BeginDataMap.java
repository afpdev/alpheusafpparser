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

import com.mgz.afp.base.IHasName;
import com.mgz.afp.base.StructuredFieldBaseTriplets;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Programming Guide and Line Data Reference (ha3l3r04.pdf), page 75<br> <br> The Begin Data Map
 * structured field begins a Data Map resource object.
 */
public class BDM_BeginDataMap extends StructuredFieldBaseTriplets implements IHasName {
  String name;
  BDM_DataFormat dataFormat;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    int actualLength = getActualLength(sfData, offset, length);

    this.name = new String(sfData, offset, 8, config.getAfpCharSet());
    if (actualLength > 8) {
      this.dataFormat = BDM_DataFormat.valueOf(sfData[offset + 8]);
      if (actualLength > 9) {
        super.decodeAFP(sfData, offset + 9, actualLength - 9, config);
      } else {
        this.setTriplets(null);
      }
    } else {
      this.dataFormat = null;
      this.setTriplets(null);
    }
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(UtilCharacterEncoding.stringToByteArray(name, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    if (dataFormat != null) {
      baos.write(dataFormat.toByte());
      if (this.getTriplets() != null) {
        for (Triplet t : this.getTriplets()) t.writeAFP(baos, config);
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String dataMapName) {
    this.name = dataMapName;
  }

  /**
   * Returns the data format specified by this Data Map.
   *
   * @return data format specified by this Data Map.
   */
  public BDM_DataFormat getDataFormat() {
    return dataFormat;
  }

  /**
   * Sets the data format specified by this {@link BDM_BeginDataMap}.
   *
   * @param dataFormat data format specified by this {@link BDM_BeginDataMap}
   */
  public void setDataFormat(BDM_DataFormat dataFormat) {
    this.dataFormat = dataFormat;
  }

  /**
   * Format of the data map of {@link BDM_BeginDataMap}
   */
  public enum BDM_DataFormat {
    UsingLND,
    UsingRCD,
    UsingXMD;

    public static BDM_DataFormat valueOf(byte dataFormtCode) {
      for (BDM_DataFormat df : values()) if (df.ordinal() == dataFormtCode) return df;
      return null;
    }

    public int toByte() {
      return ordinal();
    }
  }

}
