package com.hugui.annotationbatis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author HuGui
 * @class com.hugui.annotationbatis NeedtoSelectColumn.java
 * @date 2018年5月23日
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedtoSelectColumn {

	String columnName() default "";

}
