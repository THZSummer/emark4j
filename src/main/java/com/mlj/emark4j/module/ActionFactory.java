package com.mlj.emark4j.module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.annotation.EActionRegister;
import com.mlj.emark4j.annotation.EActionRegisterPackage;
import com.mlj.emark4j.annotation.EBuilderRegister;
import com.mlj.emark4j.annotation.EBuilderRegisterPackage;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.bean.Emark4JConfig;
import com.mlj.emark4j.module.listener.SyncDocumentListeners;
import com.mlj.emark4j.module.listener.TabbedSwitchListeners;
import com.mlj.emark4j.view.Emark4JView;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

/**
 * 2022/09/07：
 *
 * @author MLJ
 */

@EActionRegisterPackage(packages = {"com.mlj.emark4j.module.action.register"})
@EBuilderRegisterPackage(packages = {"com.mlj.emark4j.module.builder.register"})
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
                    String basePath = "";
                    if (aClass.isAnnotationPresent(EActionRegister.class)) {
                        basePath = aClass.getDeclaredAnnotation(EActionRegister.class).path();
                    }
                    Object instance = aClass.newInstance();
                    Method[] methods = aClass.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(EActionRegister.class)) {
                            EActionRegister methodDeclaredAnnotation = method.getDeclaredAnnotation(EActionRegister.class);
                            ActionListener invoke = (ActionListener) method.invoke(instance);
                            actionManager.getActionListenerMap().put(basePath + methodDeclaredAnnotation.path(), invoke);
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
                    String basePath = "";
                    if (aClass.isAnnotationPresent(EBuilderRegister.class)) {
                        basePath = aClass.getDeclaredAnnotation(EBuilderRegister.class).path();
                    }
                    Object instance = aClass.newInstance();
                    Method[] methods = aClass.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(EBuilderRegister.class)) {
                            EBuilderRegister methodDeclaredAnnotation = method.getDeclaredAnnotation(EBuilderRegister.class);
                            Builder<?> invoke = (Builder<?>) method.invoke(instance);
                            actionManager.getBuilderMap().put(basePath + methodDeclaredAnnotation.path(), invoke);
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
            Builder<?> builder = actionManager.getBuilderMap().get(jsonObject.getString("id"));
            if (null != builder) {
                builder.builder(jsonObject);
            }
        }
    }

    public static <T> T build(JSONObject jsonObject, Class<T> tClass, String pattern) {
        Builder<?> builder = actionManager.getBuilderMap().get(pattern);
        Object obj = builder.builder(jsonObject);
        if (obj.getClass() == tClass) {
            return (T) obj;
        }
        return null;
    }

    public void registerAction() {
        registerTextPaneAction();

        registerSyncDocumentListener();

        registerHeaderAction();

        registerFileAction();

        registerModeToggleAction();

        registerTabbedSwitchListener();
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

        actionManager.getMapperMap().forEach((s, actionMapper) ->
                textPane.registerKeyboardAction(actionMapper.getAction(), actionMapper.getaKeyStroke(), actionMapper.getaCondition()));
    }

    // tab
    private void registerSyncDocumentListener() {
        documentManager.getMarkDocument().addDocumentListener(new SyncDocumentListeners.EmarkDocumentListener());

        documentManager.getSourceDocument().addDocumentListener(new SyncDocumentListeners.SourceDocumentListener());
    }

    private void registerTabbedSwitchListener() {
        view.getTabbedPane().addChangeListener(new TabbedSwitchListeners());
    }

    private void registerFileAction() {
        view.getOpenFileButton().addActionListener(actionManager.getMapperMap().get(view.getOpenFileButton().getText()).getAction());
        view.getSaveFileButton().addActionListener(actionManager.getMapperMap().get(view.getSaveFileButton().getText()).getAction());
    }

    private void registerHeaderAction() {
        for (JButton headerButton : view.getHeaderButtons()) {
            headerButton.addActionListener(actionManager.getMapperMap().get(headerButton.getText()).getAction());
        }
    }

    private void registerModeToggleAction() {
        view.getDocModeToggleButton().addActionListener(actionManager.getMapperMap().get("ActionDocModeToggle").getAction());
        view.getReadonlyModeToggleButton().addActionListener(actionManager.getMapperMap().get("ActionReadonlyModeToggle").getAction());
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
