/*
Copyright 2016 Michael Knigge

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

/**
 * Visitor for all kinds of {@link StructuredField} classes.
 */
public interface StructuredFieldVisitor {

  /**
   * Handles instances of {@link StructuredField}.
   */
  public void handle(final StructuredField sf);

  /**
   * Handles instances of {@link StructuredFieldBaseData}.
   */
  public void handle(final StructuredFieldBaseData sf);

  /**
   * Handles instances of {@link StructuredFieldBaseName}.
   */
  public void handle(final StructuredFieldBaseName sf);

  /**
   * Handles instances of {@link StructuredFieldBaseNameAndTriplets}.
   */
  public void handle(final StructuredFieldBaseNameAndTriplets sf);

  /**
   * Handles instances of {@link StructuredFieldBaseRepeatingGroups}.
   */
  public void handle(final StructuredFieldBaseRepeatingGroups sf);

  /**
   * Handles instances of {@link StructuredFieldBaseTriplets}.
   */
  public void handle(final StructuredFieldBaseTriplets sf);

  /**
   * Handles instances of {@link StructuredFieldErrornouslyBuilt}.
   */
  public void handle(final StructuredFieldErrornouslyBuilt sf);
}
