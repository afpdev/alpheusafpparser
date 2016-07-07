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

import com.mgz.afp.triplets.Triplet;

import java.util.List;

/**
 * Has {@link Triplet}s.
 */
public interface IHasTriplets {

  /**
   * Returns the list of {@link Triplet}s.
   */
  public List<Triplet> getTriplets();

  /**
   * Sets the list of {@link Triplet}s.
   */
  public void setTriplets(List<Triplet> triplets);

  /**
   * Convenient method to add {@link Triplet} to the list of {@link Triplet}s.
   *
   * @param triplet {@link Triplet} to add.
   */
  public void addTriplet(Triplet triplet);

  /**
   * Convenient method to removes the given {@link Triplet} to the list of {@link Triplet}s.
   *
   * @param triplet {@link Triplet} to remove.
   */
  public void removeTriplet(Triplet triplet);
}
