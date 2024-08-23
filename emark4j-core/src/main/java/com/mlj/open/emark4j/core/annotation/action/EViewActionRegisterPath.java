package com.mlj.open.emark4j.core.annotation.action;

import com.mlj.open.emark4j.core.annotation.ERegisterPath;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2023/04/02：
 *
 * @author MLJ
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ERegisterPath
public @interface EViewActionRegisterPath {
    String[] paths() default {};
}
