package com.mlj.emark4j.bean.module;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.bean.Emark4JConfig;
import com.mlj.emark4j.bean.annotation.ModuleBuilder;
import com.mlj.emark4j.bean.annotation.ModulePackage;
import com.mlj.emark4j.view.Emark4JView;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2022/09/07：
 *
 * @author MLJ
 */
@ModulePackage(packages = {"com.mlj.emark4j.bean.module.builder"})
public class ActionFactory {
    private Emark4JConfig config;

    private Map<String, AbstractModule> builderMap;

    private static final ActionFactory defaultFactory;

    static {
        defaultFactory = new ActionFactory();
    }

    public static ActionFactory getInstance(Emark4JConfig config) {
        defaultFactory.config = config;
        defaultFactory.builderMap = new HashMap<>();
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
                    ModuleBuilder moduleBuilder = aClass.getDeclaredAnnotation(ModuleBuilder.class);
                    if (null != moduleBuilder) {
                        defaultFactory.builderMap.put(moduleBuilder.pattern(), (AbstractModule) aClass.newInstance());
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return defaultFactory;
    }

    public List<AbstractModule> builder(StyledDocument styledDocument) {
        List<AbstractModule> abstractModules = new ArrayList<>();
        JSONArray jsonArray = config.getJsonObject().getJSONArray("styles");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            AbstractModule abstractModule = builderMap.get(jsonObject.getString("pattern"));
            if (null != abstractModule) {
                abstractModule.builder(styledDocument, jsonObject);
                abstractModules.add(abstractModule);
            }
        }

        return abstractModules;
    }

    public void registerAction(Emark4JView emark4JView, DocumentManager documentManager) {
        registerCtrlKey(emark4JView.getTextPane(), documentManager.getMarkDocument());
    }

    private void registerCtrlKey(JTextPane textPane, StyledDocument styledDocument) {
        textPane.registerKeyboardAction(e -> styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), styledDocument.getStyle("Header0"), true), "Ctrl+0", KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);

        textPane.registerKeyboardAction(new HeaderAction(1, textPane, styledDocument), "Ctrl+1",
                KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        textPane.registerKeyboardAction(new HeaderAction(2, textPane, styledDocument), "Ctrl+2",
                KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        textPane.registerKeyboardAction(new HeaderAction(3, textPane, styledDocument), "Ctrl+3",
                KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        textPane.registerKeyboardAction(new HeaderAction(4, textPane, styledDocument), "Ctrl+4",
                KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        textPane.registerKeyboardAction(new HeaderAction(5, textPane, styledDocument), "Ctrl+5",
                KeyStroke.getKeyStroke(KeyEvent.VK_5, KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        textPane.registerKeyboardAction(new HeaderAction(6, textPane, styledDocument), "Ctrl+6",
                KeyStroke.getKeyStroke(KeyEvent.VK_6, KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
    }

    private static class HeaderAction implements ActionListener {

        private final int level;

        private final JTextPane textPane;

        private final StyledDocument styledDocument;

        public HeaderAction(int level, JTextPane textPane, StyledDocument styledDocument) {
            this.level = level;
            this.textPane = textPane;
            this.styledDocument = styledDocument;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean containsAttributes = textPane.getParagraphAttributes().containsAttributes(styledDocument.getStyle("Header" + level));
            Style style = containsAttributes ? styledDocument.getStyle("Header0") : styledDocument.getStyle("Header" + level);
            styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), style, true);
        }
    }

    private ActionFactory() {
    }
}
