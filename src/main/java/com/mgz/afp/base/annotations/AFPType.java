package com.mgz.afp.base.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Indicates that all fields of the annotated class are treated as if they were annotated with <code>{@link AFPField}</code>.
 * If a class is annotated with {@link AFPType}, only fields that differ from {@link AFPField} default values have to be explicitely annotated with {@link AFPField} 
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AFPType {

}
