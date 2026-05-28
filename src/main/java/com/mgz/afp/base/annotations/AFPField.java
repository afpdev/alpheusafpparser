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

package com.mgz.afp.base.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation used to describe AFP fields and their constraints.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AFPField {
  /**
   * Indicates if a field shown in editor.
   *
   * @return true if hidden, false otherwise
   */
  boolean isHidden() default false;

  /**
   * Indicates if a field is editable.
   *
   * @return true if editable, false otherwise
   */
  boolean isEditable() default true;

  /**
   * Indicates that a field is optional.
   *
   * @return true if optional, false otherwise
   */
  boolean isOptional() default false;

  /**
   * The default value in String form.
   *
   * @return the default value
   */
  String defaultValue() default "";

  /**
   * Constraints for a field value: min, max, range, list.
   *
   * @return the constraint value
   */
  String constraintValue() default "";

  /**
   * Constraints for a field size. Applies to field of type String, arrays, and collections.
   *
   * @return the size constraint
   */
  int size() default -1;

  /**
   * Minimum size constraint.
   *
   * @return the minimum size
   */
  int minSize() default -1;

  /**
   * Maximum size constraint.
   *
   * @return the maximum size
   */
  int maxSize() default -1;

  /**
   * Constraints for a field's serialized representation. Applies to field of type String, arrays,
   * and collections.
   *
   * @return the serialized size constraint
   */
  int serializedSize() default -1;

  /**
   * Minimum serialized size constraint.
   *
   * @return the minimum serialized size
   */
  int serializedMinSize() default -1;

  /**
   * Maximum serialized size constraint.
   *
   * @return the maximum serialized size
   */
  int serializedMaxSize() default -1;

  /**
   * Index nr. Used to describe interdependency of optional fields. Make sure all optional fields
   * with lower indexNr have also values.
   *
   * @return the index number
   */
  int indexNr() default -1;
}
