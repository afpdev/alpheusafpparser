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
package com.mgz.afp.triplets;

import com.mgz.afp.parser.TripletParser;
import com.mgz.afp.triplets.Triplet.TripletID;

import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TripletTest {

  public static final Logger LOG = Logger.getLogger(TripletTest.class.getSimpleName());

  @Test
  public void testTripletInstanciation() {
    LOG.log(Level.INFO, "Defined MODCA Triplets:");
    for (TripletID tID : TripletID.values()) {
      Triplet t = TripletParser.createTripletInstance(tID);
      assertNotNull(t);
      LOG.log(Level.INFO, t.getClass().getSimpleName());
      assertEquals(tID.name(), t.getClass().getSimpleName());
    }
  }

}
