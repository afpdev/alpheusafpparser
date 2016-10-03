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

import com.mgz.afp.base.StructuredFieldBaseTriplets;
import com.mgz.afp.enums.AFPOrientation;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.parser.AFPParserConfiguration;
import com.mgz.afp.triplets.Triplet;
import com.mgz.util.Constants;
import com.mgz.util.UtilBinaryDecoding;
import com.mgz.util.UtilCharacterEncoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * MO:DCA, page 217.<br> andProgramming Guide and Line Data Reference(ha3l3r04.pdf), page 101.<br>
 * <br> The Include Page Overlay structured field references an overlay resource definition that is
 * to be positioned on the page. A page overlay can be referenced at any time during the page state,
 * but not during an object state. The overlay contains its own active environment group definition.
 * The current environment of the page that included the overlay is restored when the Include Page
 * Overlay has been completed. <br> <br> The Include Page Overlay structured field references an
 * overlay resource object that is to be positioned on the page. The overlay contains its own Active
 * Environment Group. For line-mode and mixed-mode applications only, a value of X'FFFFFF' may be
 * used for either the {@link #xOrigin}, the {@link #yOrigin}, or both.
 */

public class IPO_IncludePageOverlay extends StructuredFieldBaseTriplets {
  String overlayName;
  int xOrigin;
  int yOrigin;
  AFPOrientation xRotation;

  @Override
  public void decodeAFP(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {
    overlayName = new String(sfData, offset, 8, config.getAfpCharSet());
    xOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 8, 3);
    yOrigin = UtilBinaryDecoding.parseInt(sfData, offset + 11, 3);

    int actualLength = getActualLength(sfData, offset, length);
    if (actualLength > 14) {
      xRotation = AFPOrientation.valueOf(UtilBinaryDecoding.parseInt(sfData, offset + 14, 2));
      if (actualLength > 16) {
        super.decodeAFP(sfData, offset + 16, actualLength - 16, config);
      } else {
        triplets = null;
      }
    } else {
      xRotation = null;
    }
  }


  @Override
  public void writeAFP(OutputStream os, AFPParserConfiguration config) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(UtilCharacterEncoding.stringToByteArray(overlayName, config.getAfpCharSet(), 8, Constants.EBCDIC_ID_FILLER));
    baos.write(UtilBinaryDecoding.intToByteArray(xOrigin, 3));
    baos.write(UtilBinaryDecoding.intToByteArray(yOrigin, 3));
    if (xRotation != null) {
      baos.write(xRotation.toBytes());
      if (triplets != null) {
        for (Triplet t : triplets) {
          t.writeAFP(baos, config);
        }
      }
    }
    writeFullStructuredField(os, baos.toByteArray());
  }

  public String getOverlayName() {
    return overlayName;
  }

  public void setOverlayName(String overlayName) {
    this.overlayName = overlayName;
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

  public AFPOrientation getxRotation() {
    return xRotation;
  }

  public void setxRotation(AFPOrientation xRotation) {
    this.xRotation = xRotation;
  }
}
