package com.mlj.emark4j.module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.annotation.ModuleBuilder;
import com.mlj.emark4j.annotation.ModulePackage;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.bean.Emark4JConfig;
import com.mlj.emark4j.module.builder.HeaderBuilder;
import com.mlj.emark4j.view.Emark4JView;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.*;

/**
 * 2022/09/07：
 *
 * @author MLJ
 */
@ModulePackage(packages = {"com.mlj.emark4j.module.builder"})
public class ActionFactory {
    private Emark4JConfig config;

    private Emark4JView view;

    private DocumentManager documentManager;

    private Map<String, Object> builderMap;

    private Map<String, Method> builderMethodMap;

    private ActionManager actionManager;

    private static volatile ActionFactory defaultFactory;

    public static ActionFactory getInstance() {
        if (null == defaultFactory) {
            synchronized (ActionFactory.class) {
                if (null == defaultFactory) {
                    defaultFactory = new ActionFactory();
                }
            }
        }
        return defaultFactory;
    }

    public void init(Emark4JConfig config, Emark4JView view, DocumentManager documentManager) throws Exception {
        if (null != this.config && null != this.view && null != this.documentManager) {
            throw new Exception("the factory has been init!");
        } else {
            doInit(config, view, documentManager);
        }
    }

    private void doInit(Emark4JConfig config, Emark4JView view, DocumentManager documentManager) {
        this.config = Objects.requireNonNull(config);
        this.view = view;
        this.documentManager = documentManager;
        this.builderMap = new HashMap<>();
        this.builderMethodMap = new HashMap<>();
        this.actionManager = new ActionManager();
        this.view.getTextPane().setStyledDocument(this.documentManager.getMarkDocument());

        ModulePackage declaredAnnotation = defaultFactory.getClass().getDeclaredAnnotation(ModulePackage.class);
        String[] packages = declaredAnnotation.packages();
        for (String aPackage : packages) {
            URL resource = ActionFactory.class.getClassLoader().getResource(aPackage.replace('.', '/'));
            assert resource != null;
            File path = new File(resource.getPath());
            String[] list = path.list((dir, name) -> name.endsWith(".class"));
            assert list != null;
            for (String s : list) {
                s = aPackage + "." + s.replace(".class", "");

                try {
                    Class<?> aClass = Class.forName(s);
                    Object instance = aClass.newInstance();
                    Method[] methods = aClass.getDeclaredMethods();
                    for (Method method : methods) {
                        ModuleBuilder annotation = method.getDeclaredAnnotation(ModuleBuilder.class);
                        if (method.isAnnotationPresent(ModuleBuilder.class)) {
                            builderMap.put(annotation.pattern(), instance);
                            builderMethodMap.put(annotation.pattern(), method);
                        }
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object getBuilder(String key) {
        return getInstance().builderMap.get(key);
    }

    public List<Object> builder() {
        List<Object> objects = new ArrayList<>();
        JSONArray jsonArray = config.getJsonObject().getJSONArray("styles");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Method method = builderMethodMap.get(jsonObject.getString("pattern"));
            Object instance = builderMap.get(jsonObject.getString("pattern"));
            if (null != method) {
                try {
                    method.invoke(instance, jsonObject);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return objects;
    }

    public static <T> T build(JSONObject jsonObject, Class<T> tClass, String pattern) {
        ActionFactory actionFactory = getInstance();
        Method method = actionFactory.builderMethodMap.get(pattern);
        try {
            java.lang.Object obj = method.invoke(new HeaderBuilder(), jsonObject);
            if (obj.getClass() == tClass) {
                return (T) obj;
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void registerAction() {
        JTextPane textPane = this.view.getTextPane();
        DefaultStyledDocument styledDocument = documentManager.getMarkDocument();
        Font font = styledDocument.getFont(styledDocument.getStyle(Constants.DEFAULT_STYLE_NAME));
        textPane.setFont(font);
        actionManager.getMappers().forEach(actionMapper ->
                textPane.registerKeyboardAction(actionMapper.anAction, actionMapper.aKeyStroke, actionMapper.aCondition));
    }

    public void addActionMapper(ActionManager.ActionMapper actionMapper) {
        this.actionManager.mappers.add(actionMapper);
    }

    public Emark4JConfig getConfig() {
        return config;
    }

    public Emark4JView getView() {
        return view;
    }

    public DocumentManager getDocumentManager() {
        return documentManager;
    }

    public Map<String, Object> getBuilderMap() {
        return builderMap;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    private ActionFactory() {
    }
}
