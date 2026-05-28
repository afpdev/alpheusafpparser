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

package com.mgz.afp.writer;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.base.annotations.AFPField;
import com.mgz.util.UtilCharacterEncoding;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * An implementation of {@link IAFPWriter} that produces a human-readable,
 * JSON-like representation of an AFP structured field.
 */
public class AFPWriterHumanReadable implements IAFPWriter {

  /**
   * Default constructor for AFPWriterHumanReadable.
   */
  public AFPWriterHumanReadable() {
  }

  @Override
  public String writeSF(StructuredField sf) {
    StringBuilder sb = new StringBuilder();
    sb.append(sf.getClass().getSimpleName()).append("{\n");

    Class<? extends Object> clazz = sf.getClass();
    do {
      for (Field f : clazz.getDeclaredFields()) {
        if (!f.isAnnotationPresent(AFPField.class)) {
          continue;
        }
        AFPField annotationAFPField = f.getAnnotation(AFPField.class);
        if (annotationAFPField.isHidden()) {
          continue;
        }

        String fieldName = null;
        String methodName = null;
        fieldName = f.getName();
        Method method = null;
        for (Method m : clazz.getDeclaredMethods()) {
          if (!Modifier.isPublic(m.getModifiers())) {
            continue;
          }
          methodName = m.getName();
          if (methodName.equalsIgnoreCase("get" + fieldName) || methodName.equalsIgnoreCase(fieldName)) {
            method = m;
            break;
          }
        }
        if (method == null) {
          continue;
        }

        Object o;
        try {
          o = method.invoke(sf);
          if (o == null && annotationAFPField.isOptional()) {
            continue;
          }

          if (o != null) {
            sb.append("\"").append(f.getName()).append("\":");

            if (o instanceof Number n) {
              sb.append(n);
            } else if (o instanceof Enum<?> e) {
              sb.append('"').append(e.name()).append('"');
            } else if (o instanceof CharSequence cs) {
              sb.append('"').append(cs).append('"');
            } else if (o instanceof Collection<?> || o.getClass().isArray()) {

              sb.append("[");

              if (o.getClass().isArray() && Array.getLength(o) >= 0 && Array.get(o, 0) instanceof Byte) {
                sb.append('"').append(UtilCharacterEncoding.bytesToHexString((byte[]) o)).append('"');

              } else {
                Collection<?> coll = o instanceof Collection<?> c ? c : Arrays.asList(o);
                if (!coll.isEmpty()) {
                  var firstObj = coll.iterator().next();
                  String joined;
                  if (firstObj instanceof Byte) {
                    joined = coll.stream()
                        .map(a -> "\"" + Integer.toHexString((Byte) a) + "\"")
                        .collect(Collectors.joining(","));
                  } else {
                    joined = coll.stream()
                        .map(a -> a instanceof Enum<?> e ? "\"" + e.name() + "\"" : "\"" + a + "\"")
                        .collect(Collectors.joining(","));
                  }
                  sb.append(joined);
                }
              }
              sb.append("]");
            }
            sb.append("\n");

            break;
          }
          ;

        } catch (Throwable ex) {
          sb.append(ex.getMessage() + "\n");
        }
      }

      clazz = clazz.getSuperclass();
    } while (clazz != Object.class);
    sb.append("}\n");

    return sb.toString();
  }
}
