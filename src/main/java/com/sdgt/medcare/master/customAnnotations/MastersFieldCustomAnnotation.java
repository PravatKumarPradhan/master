package com.sdgt.medcare.master.customAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MastersFieldCustomAnnotation {
    public String displayName() default "";
    public int sequence() default 99;
    public String velidationRegex() default "";
    public boolean editableByUser() default true;
    public boolean visibleToUser() default true;
    public boolean nullable() default true;
}
