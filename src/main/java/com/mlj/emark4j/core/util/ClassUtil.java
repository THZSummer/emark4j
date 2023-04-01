package com.mlj.emark4j.core.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 2023/03/05：
 * 包扫描参考：org.springframework.context.annotation.ClassPathBeanDefinitionScanner#scan
 *
 * @author MLJ
 */
public class ClassUtil {

    public static void scanPackages(String[] packages, Class<? extends Annotation> annotationClass,
                                    Consumer<Annotation> classAnnotationConsumer,
                                    BiConsumer<Annotation, Field> fieldAnnotationConsumer,
                                    BiConsumer<Annotation, Object> methodAnnotationConsumer) {
        for (String aPackage : packages) {
            String basePath = aPackage.replace('.', '/');
            URL resource = ClassUtil.class.getClassLoader().getResource(basePath);
            assert resource != null;
            String protocol = resource.getProtocol();
            File path = new File(resource.getPath());

            if ("jar".equals(protocol)) {
                String path1 = resource.getPath();
                path1 = path1.substring(path1.indexOf(':') + 2);
                path1 = path1.substring(0, path1.indexOf('!')).replaceAll("\\\\", "/");
                System.out.println(path1);
                scanJarPath(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodAnnotationConsumer, aPackage, path1, basePath);
            } else {
                scanPath(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodAnnotationConsumer, aPackage, path);
            }
        }
    }

    private static void scanJarPath(Class<? extends Annotation> annotationClass,
                                    Consumer<Annotation> classAnnotationConsumer,
                                    BiConsumer<Annotation, Field> fieldAnnotationConsumer,
                                    BiConsumer<Annotation, Object> methodAnnotationConsumer, String aPackage, String path, String basePath) {
        try {
            JarFile jarFile = new JarFile(path);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String name = jarEntry.getName();
                if (name.startsWith(basePath) && name.endsWith(".class")) {
                    System.out.println(name);
                    String className = name.replace(".class", "").replaceAll("/", ".");
                    System.out.println(className);
                    scanClass(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodAnnotationConsumer, className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scanPath(Class<? extends Annotation> annotationClass,
                                 Consumer<Annotation> classAnnotationConsumer,
                                 BiConsumer<Annotation, Field> fieldAnnotationConsumer,
                                 BiConsumer<Annotation, Object> methodAnnotationConsumer, String aPackage, File path) {
        File[] files = path.listFiles();
        if (null != files) {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanPath(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodAnnotationConsumer,
                            aPackage + "." + file.getName(), file);
                } else {
                    if (file.getName().endsWith("class")) {
                        String s = aPackage + "." + file.getName().replace(".class", "");
                        scanClass(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodAnnotationConsumer, s);
                    }
                }
            }
        }
    }

    private static void scanClass(Class<? extends Annotation> annotationClass,
                                  Consumer<Annotation> classAnnotationConsumer,
                                  BiConsumer<Annotation, Field> fieldAnnotationConsumer,
                                  BiConsumer<Annotation, Object> methodAnnotationConsumer, String className) {

        try {
            // 1.类注解扫描
            Class<?> aClass = Class.forName(className);
            if (null != classAnnotationConsumer) {
                if (aClass.isAnnotationPresent(annotationClass)) {
                    classAnnotationConsumer.accept(aClass.getDeclaredAnnotation(annotationClass));
                }
            }
            // 2.属性注解扫描
            if (null != fieldAnnotationConsumer) {
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (declaredField.isAnnotationPresent(annotationClass)) {
                        Annotation fieldDeclaredAnnotation = declaredField.getDeclaredAnnotation(annotationClass);
                        fieldAnnotationConsumer.accept(fieldDeclaredAnnotation, declaredField);
                    }
                }
            }
            // 3.方法注解扫描
            if (null != methodAnnotationConsumer) {
                Object instance = aClass.newInstance();
                Method[] methods = aClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(annotationClass)) {
                        Annotation methodDeclaredAnnotation = method.getDeclaredAnnotation(annotationClass);
                        Object invoke = method.invoke(instance);
                        methodAnnotationConsumer.accept(methodDeclaredAnnotation, invoke);
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void scanPackagesMethod(String[] packages, Class<? extends Annotation> annotationClass,
                                          BiConsumer<Annotation, Object> methodAnnotationConsumer) {
        scanPackages(packages, annotationClass, null, null, methodAnnotationConsumer);
    }
}
