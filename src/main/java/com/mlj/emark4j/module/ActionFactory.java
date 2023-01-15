package com.mlj.emark4j.module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.annotation.*;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.bean.Emark4JConfig;
import com.mlj.emark4j.module.listener.SyncDocumentListeners;
import com.mlj.emark4j.module.listener.TabbedSwitchListeners;
import com.mlj.emark4j.view.EButton;
import com.mlj.emark4j.view.Emark4JView;
import com.mlj.emark4j.view.binder.ActionListenerBind2ButtonBinder;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

/**
 * 2022/09/07：
 *
 * @author MLJ
 */

@EActionRegisterPackage(packages = "com.mlj.emark4j.module.action.register")
@EBuilderRegisterPackage(packages = "com.mlj.emark4j.module.builder.register")
@EBinderRegisterPackage(packages = "com.mlj.emark4j.view.binder.register")
@EBindPackage(packages = "com.mlj.emark4j.view.binder.bind")
public class ActionFactory {
    private static Emark4JConfig config;

    private static Emark4JView view;

    private static DocumentManager documentManager;

    private static final ActionManager actionManager;

    private static final StyleManager styleManager;

    private static final ActionFactory defaultFactory;

    static {
        actionManager = new ActionManager();
        styleManager = new StyleManager();
        defaultFactory = new ActionFactory();
    }

    public static ActionFactory getInstance() {
        return defaultFactory;
    }

    public void init(Emark4JConfig config, Emark4JView view, DocumentManager documentManager) throws Exception {
        if (null != ActionFactory.config && null != ActionFactory.view && null != ActionFactory.documentManager) {
            throw new Exception("the factory has been init!");
        } else {
            doInit(config, view, documentManager);
        }
    }

    private void doInit(Emark4JConfig config, Emark4JView view, DocumentManager documentManager) {
        ActionFactory.config = Objects.requireNonNull(config);
        ActionFactory.view = view;
        ActionFactory.documentManager = documentManager;

        initActionRegister();
        initBuilderRegister();
        initBinderRegister();

        initBind();
    }

    private void initActionRegister() {
        EActionRegisterPackage declaredAnnotation = defaultFactory.getClass().getDeclaredAnnotation(EActionRegisterPackage.class);
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
                        if (method.isAnnotationPresent(EActionRegisterPath.class)) {
                            EActionRegisterPath methodDeclaredAnnotation = method.getDeclaredAnnotation(EActionRegisterPath.class);
                            ActionListener action = (ActionListener) method.invoke(instance);
                            if (methodDeclaredAnnotation.paths().length > 0) {
                                actionManager.getActionListenerMap().put(methodDeclaredAnnotation.paths()[0], action);
                            }
                        }
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initBuilderRegister() {
        EBuilderRegisterPackage declaredAnnotation = defaultFactory.getClass().getDeclaredAnnotation(EBuilderRegisterPackage.class);
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
                        if (method.isAnnotationPresent(EBuilderRegisterPath.class)) {
                            EBuilderRegisterPath methodDeclaredAnnotation = method.getDeclaredAnnotation(EBuilderRegisterPath.class);
                            if (methodDeclaredAnnotation.paths().length > 0) {
                                EBuilder<?> invoke = (EBuilder<?>) method.invoke(instance);
                                actionManager.getBuilderMap().put(methodDeclaredAnnotation.paths()[0], invoke);
                            }
                        }
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initBinderRegister() {
        EBinderRegisterPackage declaredAnnotation = defaultFactory.getClass().getDeclaredAnnotation(EBinderRegisterPackage.class);
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
                        if (method.isAnnotationPresent(EBinderRegisterPath.class)) {
                            EBinderRegisterPath methodDeclaredAnnotation = method.getDeclaredAnnotation(EBinderRegisterPath.class);
                            if (methodDeclaredAnnotation.paths().length > 0) {
                                EBinder<?, ?> invoke = (EBinder<?, ?>) method.invoke(instance);
                                actionManager.getBinderMap().put(methodDeclaredAnnotation.paths()[0], invoke);
                            }
                        }
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initBind() {
        EBindPackage declaredAnnotation = defaultFactory.getClass().getDeclaredAnnotation(EBindPackage.class);
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
                    for (Method field : methods) {
                        if (field.isAnnotationPresent(EBindPath.class)) {
                            EBindPath methodDeclaredAnnotation = field.getDeclaredAnnotation(EBindPath.class);
                            String[] paths = methodDeclaredAnnotation.paths();
                            if (paths.length > 0) {
                                Object obj = field.invoke(instance);

                                if (paths.length == 1) {
                                    actionManager.getActionListenerBind2ButtonBinderMap().put(paths[0], (EButton) obj);
                                } else {
                                    actionManager.getKeyboardActionBind2ComponentBinderMap().put((JComponent) obj, paths);
                                }
                            }
                        }
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void builder() {
        JSONArray jsonArray = config.getJsonObject().getJSONArray("styles");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            EBuilder<?> eBuilder = actionManager.getBuilderMap().get(jsonObject.getString("id"));
            if (null != eBuilder) {
                eBuilder.builder(jsonObject);
            }
        }
    }

    public static <T> T build(JSONObject jsonObject, Class<T> tClass, String pattern) {
        EBuilder<?> eBuilder = actionManager.getBuilderMap().get(pattern);
        Object obj = eBuilder.builder(jsonObject);
        if (obj.getClass() == tClass) {
            return (T) obj;
        }
        return null;
    }

    public void registerAction() {
        binder();

        registerSyncDocumentListener();

        registerTabbedSwitchListener();
    }

    public void binder() {
        actionManager.getActionListenerBind2ButtonBinderMap().forEach((s, button) ->
                ((ActionListenerBind2ButtonBinder) actionManager.getBinderMap().get(s))
                        .binder(actionManager.getActionListenerMap().get(s), button));

        registerTextPaneAction();
    }

    public void openNewTab(URI uri) {
        DocumentManager.createNewDocumentManager(uri);
        view.createNewTabbed(new File(uri).getName());
        view.getTabbedPane().setSelectedIndex(view.getTabbedPane().getTabCount() - 1);
        registerTextPaneAction();
        registerSyncDocumentListener();
    }

    public void closeTab(String name) {

    }

    public void renameTabTo(String newName) {
        documentManager.renameDocumentTo(newName);
        view.renameTabbed(newName);
    }

    // tab
    private void registerTextPaneAction() {
        JTextPane textPane = view.getTextPane();
        DefaultStyledDocument styledDocument = documentManager.getMarkDocument();
        textPane.setStyledDocument(styledDocument);
        Style defaultStyle = styleManager.getMapperMap().get(Constants.DEFAULT_STYLE_NAME).getStyle();
        textPane.setFont(styledDocument.getFont(defaultStyle));
        textPane.setBackground(styledDocument.getBackground(defaultStyle));
        textPane.setForeground(styledDocument.getForeground(defaultStyle));
        textPane.setDisabledTextColor(styledDocument.getForeground(defaultStyle));

        Map<String, ActionManager.ActionMapper> mapperMap = actionManager.getMapperMap();
        for (Map.Entry<JComponent, String[]> entry : actionManager.getKeyboardActionBind2ComponentBinderMap().entrySet()) {
            JComponent component = entry.getKey();
            String[] strings = entry.getValue();
            for (String string : strings) {
                ActionManager.ActionMapper actionMapper = mapperMap.get(string);
                component.registerKeyboardAction(actionMapper.getAction(), actionMapper.getaKeyStroke(), actionMapper.getaCondition());
            }
        }
    }

    // tab
    private void registerSyncDocumentListener() {
        documentManager.getMarkDocument().addDocumentListener(new SyncDocumentListeners.EmarkDocumentListener());

        documentManager.getSourceDocument().addDocumentListener(new SyncDocumentListeners.SourceDocumentListener());
    }

    private void registerTabbedSwitchListener() {
        view.getTabbedPane().addChangeListener(new TabbedSwitchListeners());
    }

    public static boolean isRead() {
        return documentManager.getEditMode().isRead();
    }

    public static boolean isWrite() {
        return documentManager.getEditMode().isWrite();
    }

    public static boolean isSource() {
        return documentManager.getEditMode().isSource();
    }

    public static boolean isEmark() {
        return documentManager.getEditMode().isEmark();
    }

    public void addActionMapper(ActionManager.ActionMapper actionMapper) {
        actionManager.getMapperMap().put(actionMapper.getName(), actionMapper);
    }

    public void addStyleMapper(StyleManager.StyleMapper styleMapper) {
        styleManager.getMapperMap().put(styleMapper.getName(), styleMapper);
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

    public ActionManager getActionManager() {
        return actionManager;
    }

    public StyleManager getStyleManager() {
        return styleManager;
    }

    private ActionFactory() {
    }
}
