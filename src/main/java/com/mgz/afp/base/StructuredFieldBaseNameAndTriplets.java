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

import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.enums.SFTypeID;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class StructuredFieldBaseNameAndTriplets extends StructuredFieldBaseName implements IHasTriplets {

  private byte[] reserved8_9;

  @AFPField
  private List<Triplet> triplets;


  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    super.decodeAFP(sfData, offset, length, config);
    final int actualLength = getActualLength(sfData, offset, length);

    // Old "historical" BTD and BRS structured fields may come up without the two reserved bytes...
    if (this.isBeginResourceOrBeginDocument() && actualLength > 8) {
      this.reserved8_9 = new byte[]{sfData[offset + 8], sfData[offset + 9]};
    } else {
      this.reserved8_9 = null;
    }

    final int offsetToTriplets = this.getOffsetToTriplets();
    if (actualLength > offsetToTriplets) {
      triplets = TripletParser.parseTriplets(sfData, offsetToTriplets, sfData.length - offsetToTriplets, config);
    } else {
      triplets = null;
    }
  }

  /**
   * On all structured fields the triplets will begin on offset 8 - only BRS (Begin Resource) and BDT (Begin
   * Document) are different. A two byte buffer (reserved bytes) *MAY* be present. In "historical" MO:DCA
   * specifications are BRS and BDT structured fields described, that have *NO* triplets and those two reserved
   * bytes furthermore are also optional... We need to support those "old" MO:DCA files, too.
   */
  private int getOffsetToTriplets() {
    return this.isBeginResourceOrBeginDocument() ? 10 : 8;
  }

  private boolean isBeginResourceOrBeginDocument() {
    SFTypeID sfid = this.getStructuredFieldIntroducer().getSFTypeID();
    return sfid == SFTypeID.BRS_BeginResource || sfid == SFTypeID.BDT_BeginDocument;
  }

  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    if (this.getName() != null) {
      baos.write(UtilCharacterEncoding.stringToByteArray(this.getName(), config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    }

    // Even if we've not read any triplet - one or more triplets may have been added in the meantime.
    // In this case we definitely have to write write the two "reserved bytes" on BDT and BRS structured fields.
    if (triplets != null || (isBeginResourceOrBeginDocument() && this.reserved8_9 != null)) {
      baos.write(this.reserved8_9 != null ? this.reserved8_9 : new byte[] { 0x00, 0x00 } );
    }

    if (triplets != null) {
      for (Triplet triplet : triplets) {
        triplet.writeAFP(baos, config);
      }
    }

    writeFullStructuredField(os, baos.toByteArray());
  }

  @Override
  public final List<Triplet> getTriplets() {
    return triplets;
  }

  @Override
  public final void setTriplets(List<Triplet> triplets) {
    this.triplets = triplets;
  }

  @Override
  public final void addTriplet(Triplet triplet) {
    if (triplet != null) {
      if (triplets == null) {
        triplets = new ArrayList<Triplet>();
      }
      triplets.add(triplet);
    }
  }

  @Override
  public final void removeTriplet(Triplet triplet) {
    if (triplet != null && triplets != null) {
      triplets.remove(triplet);
    }
  }

  @Override
  public void accept(final StructuredFieldVisitor visitor) {
    visitor.handle(this);
  }
}
