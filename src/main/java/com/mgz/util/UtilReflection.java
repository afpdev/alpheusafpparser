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
package com.mgz.util;

import com.mgz.afp.base.annotations.AFPField;
import com.mgz.afp.base.annotations.AFPType;
import com.mgz.afp.exceptions.AFPParserException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class UtilReflection {

  public static final Comparator<Field> comparatorFields = new FieldComparator();
  private static AFPField annotationAFPField;
  public static final AFPField defaultAFPFieldAnnotation = getAFPFieldDefaultAnnotation();

  public static Object getFieldValue(Field field, Object instance) throws AFPParserException {
    if (instance == null) {
      return null;
    }

    String errMsg = "";

    // Try to get value by accessing the field.
    try {
      boolean isAccessable = field.isAccessible();
      field.setAccessible(true);
      Object val = field.get(instance);
      field.setAccessible(isAccessable);
      return val;
    } catch (IllegalArgumentException | IllegalAccessException e1) {
      // We don't give up.
      errMsg = e1.getMessage();
    }

    // If this failed go for getter method.
    String methodName = "get" + field.getName();
    Class<?> clazz = instance.getClass();
    for (Method method : clazz.getMethods()) {
      if (methodName.equalsIgnoreCase(method.getName())) {
        Object returnValue = null;
        try {
          returnValue = method.invoke(instance);
        } catch (Throwable e) {
          throw new AFPParserException("Failed to get value of field " + field.getName()
              + "\n" + errMsg, e);
        }
        return returnValue;
      }
    }

    return null;
  }

  public static void setFieldValue(Field field, Object instance, Object value) throws AFPParserException {
    // Try to set value by accessing the field.
    try {
      boolean isAccessable = field.isAccessible();
      field.setAccessible(true);
      field.set(instance, value);
      field.setAccessible(isAccessable);
    } catch (IllegalArgumentException | IllegalAccessException e1) {
      throw new AFPParserException("Failed to set value.", e1);
    }
  }

  public static boolean isNumeric(Class<?> fieldType) {
    return Number.class.isAssignableFrom(fieldType)
        || fieldType.isPrimitive() && (
        double.class.isAssignableFrom(fieldType)
            || float.class.isAssignableFrom(fieldType)
            || long.class.isAssignableFrom(fieldType)
            || int.class.isAssignableFrom(fieldType)
            || short.class.isAssignableFrom(fieldType)
            || byte.class.isAssignableFrom(fieldType)
    );
  }

  public static boolean isAFPType(Class<?> clazz) {
    while (clazz != null && clazz != Object.class) {
      if (clazz.getAnnotation(AFPType.class) != null) {
        return true;
      }
      for (Field field : clazz.getDeclaredFields()) {
        if (field.getAnnotation(AFPField.class) != null) {
          return true;
        }
      }
      clazz = clazz.getSuperclass();
    }
    return false;
  }

  public static AFPField getAFPFieldDefaultAnnotation() {
    if (annotationAFPField != null) {
      return annotationAFPField;
    }

    AFPFieldAnnotationBearer afpFieldAnnotationBearer = new AFPFieldAnnotationBearer();
    return annotationAFPField = afpFieldAnnotationBearer.getClass().getAnnotation(AFPField.class);
  }

  public static List<Field> getAFPFields(Class<?> clazz) {
    AFPField defaultAnnotation = null;


    List<Class<?>> listOfClasses = new ArrayList<Class<?>>();
    while (clazz != null && clazz != Object.class) {
      listOfClasses.add(0, clazz);
      clazz = clazz.getSuperclass();
    }

    List<Field> listOfFields = new ArrayList<Field>();

    for (Class<?> c : listOfClasses) {
      if (defaultAnnotation == null) {
        defaultAnnotation = c.getAnnotation(AFPType.class) != null ? defaultAFPFieldAnnotation : defaultAnnotation;
      }

      for (Field field : c.getDeclaredFields()) {
        if (Modifier.isStatic(field.getModifiers())) {
          continue;
        }
        AFPField annotation = field.getAnnotation(AFPField.class);
        if (annotation == null) {
          annotation = defaultAnnotation;
        }
        if (annotation == null || annotation.isHidden()) {
          continue;
        }

        listOfFields.add(field);
      }
    }

    Collections.sort(listOfFields, comparatorFields);

    return listOfFields;
  }

  @AFPField
  private static class AFPFieldAnnotationBearer {
  }

  public static class FieldComparator implements Comparator<Field> {

    private static final Map<String, Integer> specialOrderedFields = new HashMap<String, Integer>();

    {
      specialOrderedFields.put("extension", Integer.valueOf(1000));
      specialOrderedFields.put("padding", Integer.valueOf(1000));
    }

    private static Integer getOrder(String fieldName) {
      Integer order = specialOrderedFields.get(fieldName);
      if (order == null) {
        return Integer.valueOf(100);
      } else {
        return order;
      }
    }

    @Override
    public int compare(Field o1, Field o2) {
      return getOrder(o1.getName()).compareTo(getOrder(o2.getName()));
    }

  }

}
