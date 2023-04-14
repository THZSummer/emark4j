package com.mlj.open.emark4j.core.util;

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
                                    Consumer<ScanResult> methodConsumer,
                                    Consumer<ScanResult> methodInvokedConsumer) {
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
                scanJarPath(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodConsumer, methodInvokedConsumer, aPackage, path1, basePath);
            } else {
                scanPath(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodConsumer, methodInvokedConsumer, aPackage, path);
            }
        }
    }

    private static void scanJarPath(Class<? extends Annotation> annotationClass,
                                    Consumer<Annotation> classAnnotationConsumer,
                                    BiConsumer<Annotation, Field> fieldAnnotationConsumer,
                                    Consumer<ScanResult> methodConsumer,
                                    Consumer<ScanResult> methodInvokedConsumer, String aPackage, String path, String basePath) {
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
                    scanClass(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodConsumer, methodInvokedConsumer, className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scanPath(Class<? extends Annotation> annotationClass,
                                 Consumer<Annotation> classAnnotationConsumer,
                                 BiConsumer<Annotation, Field> fieldAnnotationConsumer,
                                 Consumer<ScanResult> methodConsumer,
                                 Consumer<ScanResult> methodInvokedConsumer, String aPackage, File path) {
        File[] files = path.listFiles(f -> !f.getName().contains("$"));
        if (null != files) {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanPath(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodConsumer, methodInvokedConsumer,
                            aPackage + "." + file.getName(), file);
                } else {
                    if (file.getName().endsWith("class")) {
                        String s = aPackage + "." + file.getName().replace(".class", "");
                        scanClass(annotationClass, classAnnotationConsumer, fieldAnnotationConsumer, methodConsumer, methodInvokedConsumer, s);
                    }
                }
            }
        }
    }

    /**
     * 类扫描
     *
     * @param targetAnnotation        目标类
     * @param classAnnotationConsumer classAnnotationConsumer
     * @param fieldAnnotationConsumer fieldAnnotationConsumer
     * @param methodConsumer          methodConsumer
     * @param methodInvokedConsumer   methodInvokedConsumer
     * @param className               className
     */
    private static void scanClass(Class<? extends Annotation> targetAnnotation,
                                  Consumer<Annotation> classAnnotationConsumer,
                                  BiConsumer<Annotation, Field> fieldAnnotationConsumer,
                                  Consumer<ScanResult> methodConsumer,
                                  Consumer<ScanResult> methodInvokedConsumer, String className) {

        ScanResult scanResult = new ScanResult();
        try {
            // 1.类注解扫描
            Class<?> aClass = Class.forName(className);
            scanResult.setTargetClass(aClass);
            if (null != classAnnotationConsumer) {
                if (aClass.isAnnotationPresent(targetAnnotation)) {
                    Annotation aClassDeclaredAnnotation = aClass.getDeclaredAnnotation(targetAnnotation);
                    scanResult.setTargetClassAnnotation(aClassDeclaredAnnotation);
                    classAnnotationConsumer.accept(aClassDeclaredAnnotation);
                }
            }
            // 2.属性注解扫描
            if (null != fieldAnnotationConsumer) {
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    scanResult.setTargetField(declaredField);
                    if (declaredField.isAnnotationPresent(targetAnnotation)) {
                        Annotation fieldDeclaredAnnotation = declaredField.getDeclaredAnnotation(targetAnnotation);
                        scanResult.setTargetFieldAnnotation(fieldDeclaredAnnotation);
                        fieldAnnotationConsumer.accept(fieldDeclaredAnnotation, declaredField);
                    }
                }
            }
            // 3.方法注解扫描，不执行
            if (null != methodConsumer || null != methodInvokedConsumer) {
                Object instance = aClass.newInstance();
                scanResult.setTargetInstance(instance);
                Method[] methods = aClass.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(targetAnnotation)) {
                        scanResult.setTargetMethod(method);
                        Annotation methodDeclaredAnnotation = method.getDeclaredAnnotation(targetAnnotation);
                        scanResult.setTargetMethodAnnotation(methodDeclaredAnnotation);
                        if (null != methodConsumer) {
                            methodConsumer.accept(scanResult);
                        }
                        // 4.方法注解扫描，执行
                        if (null != methodInvokedConsumer) {
                            Object invoke = method.invoke(instance);
                            scanResult.setTargetMethodResponse(invoke);
                            methodInvokedConsumer.accept(scanResult);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void scanPackagesMethodResponse(String[] packages, Class<? extends Annotation> annotationClass,
                                                  Consumer<ScanResult> methodInvokedConsumer) {
        scanPackages(packages, annotationClass, null, null, null, methodInvokedConsumer);
    }

    public static void scanPackagesMethod(String[] packages, Class<? extends Annotation> annotationClass,
                                          Consumer<ScanResult> methodConsumer) {
        scanPackages(packages, annotationClass, null, null, methodConsumer, null);
    }
}
