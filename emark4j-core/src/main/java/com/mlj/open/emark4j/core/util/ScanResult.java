package com.mlj.open.emark4j.core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 2023/04/06：
 *
 * @author MLJ
 */
public class ScanResult {

    private Class<?> targetClass;

    private Annotation targetClassAnnotation;

    private Field targetField;

    private Annotation targetFieldAnnotation;

    private Method targetMethod;

    private Annotation targetMethodAnnotation;

    private Object targetInstance;

    private Object targetMethodResponse;


    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Annotation getTargetClassAnnotation() {
        return targetClassAnnotation;
    }

    public void setTargetClassAnnotation(Annotation targetClassAnnotation) {
        this.targetClassAnnotation = targetClassAnnotation;
    }

    public Field getTargetField() {
        return targetField;
    }

    public void setTargetField(Field targetField) {
        this.targetField = targetField;
    }

    public Annotation getTargetFieldAnnotation() {
        return targetFieldAnnotation;
    }

    public void setTargetFieldAnnotation(Annotation targetFieldAnnotation) {
        this.targetFieldAnnotation = targetFieldAnnotation;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }

    public Annotation getTargetMethodAnnotation() {
        return targetMethodAnnotation;
    }

    public void setTargetMethodAnnotation(Annotation targetMethodAnnotation) {
        this.targetMethodAnnotation = targetMethodAnnotation;
    }

    public Object getTargetInstance() {
        return targetInstance;
    }

    public void setTargetInstance(Object targetInstance) {
        this.targetInstance = targetInstance;
    }

    public Object getTargetMethodResponse() {
        return targetMethodResponse;
    }

    public void setTargetMethodResponse(Object targetMethodResponse) {
        this.targetMethodResponse = targetMethodResponse;
    }
}
