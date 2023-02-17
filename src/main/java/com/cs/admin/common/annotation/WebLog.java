package com.cs.admin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author free loop
 * @version 1.0
 * @since 2020/8/14 23:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebLog {

    /**
     * 日志描叙说明
     *
     * @return string
     */
    String value() default "";
}
