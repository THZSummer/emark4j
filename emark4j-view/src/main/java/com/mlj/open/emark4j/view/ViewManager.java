package com.mlj.open.emark4j.view;

import com.mlj.open.emark4j.core.Emark4JWare;
import com.mlj.open.emark4j.core.annotation.binder.EBindPackage;
import com.mlj.open.emark4j.core.annotation.binder.EBindPath;
import com.mlj.open.emark4j.core.annotation.binder.EBinderRegisterPackage;
import com.mlj.open.emark4j.core.annotation.binder.EBinderRegisterPath;
import com.mlj.open.emark4j.core.bean.Constants;
import com.mlj.open.emark4j.core.bean.EditMode;
import com.mlj.open.emark4j.core.module.*;
import com.mlj.open.emark4j.core.util.ClassUtil;
import com.mlj.open.emark4j.view.binder.ActionListenerBind2ButtonBinder;
import com.mlj.open.emark4j.view.listener.TabbedSwitchListeners;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.net.URI;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 2023/04/01：
 *
 * @author MLJ
 */
public class ViewManager implements Emark4JWare {
    private static final ViewManager DEFAULT_VIEW_MANAGER;
    private static final Emark4JView emark4JView;
    private static final ActionManager actionManager;
    private static final DocumentManagerV documentManagerV;


    // --------------------------------
    private static final Map<String, EButton> actionListenerBind2ButtonBinderMap;
    private static final Map<JComponent, String[]> keyboardActionBind2ComponentBinderMap;


    static {
        DEFAULT_VIEW_MANAGER = new ViewManager();
        emark4JView = new Emark4JView();
        actionManager = ActionManager.getInstance();
        documentManagerV = DocumentManagerV.createNewDocumentManager();

        actionListenerBind2ButtonBinderMap = new HashMap<>();
        keyboardActionBind2ComponentBinderMap = new HashMap<>();
    }

    public static void run(Class<?> primarySource, String... args) {
        emark4JView.createNewTabbed(DocumentManager.getDocumentURI().getPath());
    }

    public static ViewManager getInstance() {
        return DEFAULT_VIEW_MANAGER;
    }

    public static Emark4JView getEmark4JView() {
        return emark4JView;
    }

    @Override
    public void openDocument(URI uri) {
        documentManagerV.openDocument(uri);
        emark4JView.getFrame().setTitle("Emark4J - " + uri.getPath());
        emark4JView.createNewTabbed(uri.getPath());
        emark4JView.getTabbedPane().setSelectedIndex(emark4JView.getTabbedPane().getTabCount() - 1);

        registerTextPaneAction();
    }

    public static void registerAction() {
        binder();
        registerTabbedSwitchListener();
    }

    public static void binder() {
        actionListenerBind2ButtonBinderMap.forEach((s, button) ->
                ((ActionListenerBind2ButtonBinder) actionManager.getBinderMap().get(s))
                        .binder(actionManager.getActionListenerMap().get(s), button));

    }

    // tab
    private static void registerTextPaneAction() {
        JTextPane textPane = ViewManager.getEmark4JView().getTextPane();
        DefaultStyledDocument styledDocument = DocumentManagerV.getEditMode().isEmark() ? DocumentManagerV.getMarkDocument() : DocumentManagerV.getSourceDocument();
        textPane.setStyledDocument(styledDocument);
        Style defaultStyle = ActionFactory.getInstance().getStyleManager().getMapperMap().get(Constants.DEFAULT_STYLE_NAME).getStyle();
        textPane.setFont(styledDocument.getFont(defaultStyle));
        textPane.setBackground(styledDocument.getBackground(defaultStyle));
        textPane.setForeground(styledDocument.getForeground(defaultStyle));
        textPane.setDisabledTextColor(styledDocument.getForeground(defaultStyle));

        Map<String, ActionMapper> mapperMap = actionManager.getMapperMap();
        for (Map.Entry<JComponent, String[]> entry : keyboardActionBind2ComponentBinderMap.entrySet()) {
            JComponent component = entry.getKey();
            String[] strings = entry.getValue();
            for (String string : strings) {
                ActionMapper actionMapper = mapperMap.get(string);
                component.registerKeyboardAction(actionMapper.getAction(), actionMapper.getaKeyStroke(), JComponent.WHEN_IN_FOCUSED_WINDOW);
            }
        }
    }

    @Override
    public void updateDocument(long lineNum, String line) {
        System.out.println(MessageFormat.format("{0} : [{1}]", lineNum, line));
        DefaultStyledDocument document = DocumentManagerV.getEditMode().isEmark() ? DocumentManagerV.getMarkDocument() : DocumentManagerV.getSourceDocument();
        try {
            document.insertString(document.getLength(), line + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readDocument(long lineNum) {
        return null;
    }

    @Override
    public void closeDocument(URI uri) {

    }

    @Override
    public void switchDocument(URI uri) {
        documentManagerV.switchDocument(uri);
        refreshTabStatus();
    }

    @Override
    public void saveDocument(URI uri) {

    }

    @Override
    public void renameDocumentTo(String newName) {
        emark4JView.renameTabbed(newName);
    }

    public static void registerTabbedSwitchListener() {
        emark4JView.getTabbedPane().addChangeListener(new TabbedSwitchListeners());
        registerTextPaneAction();
    }

    private static void refreshTabStatus() {
        refreshFrameStatus();
        refreshLeftTreeStatus();
        refreshBottomToolBarStatus();
    }

    private static void refreshBottomToolBarStatus() {
        EditMode editMode = DocumentManagerV.getEditMode();
        emark4JView.getDocModeToggleButton().setText(editMode.name());
        emark4JView.getReadonlyModeToggleButton().setText(editMode.isRead() ? "Readonly[true]" : "Readonly[false]");
    }

    private static void refreshLeftTreeStatus() {

    }

    private static void refreshFrameStatus() {
        emark4JView.getFrame().setTitle("Emark4J - " + DocumentManagerV.getDocumentURI().getPath());
    }

    public void initView(Class<?> primarySource, String... args) {
        initBinderRegister(primarySource, args);
        initBind(primarySource, args);
    }

    private void initBinderRegister(Class<?> primarySource, String... args) {
        EBinderRegisterPackage declaredAnnotation = primarySource.getDeclaredAnnotation(EBinderRegisterPackage.class);
        String[] packages = declaredAnnotation.packages();

        ClassUtil.scanPackagesMethodResponse(packages, EBinderRegisterPath.class, scanResult -> {
            String[] paths = ((EBinderRegisterPath) scanResult.getTargetMethodAnnotation()).paths();
            if (paths.length > 0) {
                actionManager.getBinderMap().put(paths[0], (EBinder<?, ?>) scanResult.getTargetMethodResponse());
            }
        });
    }

    private void initBind(Class<?> primarySource, String... args) {
        EBindPackage declaredAnnotation = primarySource.getDeclaredAnnotation(EBindPackage.class);
        String[] packages = declaredAnnotation.packages();

        ClassUtil.scanPackagesMethodResponse(packages, EBindPath.class, scanResult -> {
            String[] paths = ((EBindPath) scanResult.getTargetMethodAnnotation()).paths();
            if (paths.length > 0) {

                if (paths.length == 1) {
                    actionListenerBind2ButtonBinderMap.put(paths[0], (EButton) scanResult.getTargetMethodResponse());
                } else {
                    keyboardActionBind2ComponentBinderMap.put((JComponent) scanResult.getTargetMethodResponse(), paths);
                }
            }
        });
    }

    public static DocumentManagerV getDocumentManagerV() {
        return documentManagerV;
    }
}
