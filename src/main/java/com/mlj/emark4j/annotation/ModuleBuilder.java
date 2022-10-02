package com.mlj.emark4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2022/09/10：
 *
 * @author MLJ
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleBuilder {
    String pattern() default "";

    Class<?> clazz() default void.class;
}
