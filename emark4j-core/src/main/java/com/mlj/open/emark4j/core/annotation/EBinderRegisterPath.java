package com.mlj.open.emark4j.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ERegisterPath
public @interface EBinderRegisterPath {
    String[] paths() default {};
}
