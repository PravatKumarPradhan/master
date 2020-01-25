package com.sdgt.medcare.master.customAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MastersEntityCustomAnnotation {
    public String displayName() default "";
    public  String accessCategory() default "";
    public String moduleCategory() default "General";
    boolean showOnFrontEnd() default false;
}
