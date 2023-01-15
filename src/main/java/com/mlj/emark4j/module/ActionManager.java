package com.mlj.emark4j.module;

import com.mlj.emark4j.view.EButton;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022/09/17：
 *
 * @author MLJ
 */
public class ActionManager {
    private final Map<String, ActionListener> actionListenerMap;
    private final Map<String, EBuilder<?>> builderMap;
    private final Map<String, EBinder<?, ?>> binderMap;
    private final Map<String, EButton> actionListenerBind2ButtonBinderMap;
    private final Map<JComponent, String[]> keyboardActionBind2ComponentBinderMap;
    private final Map<String, ActionMapper> mapperMap;

    public ActionManager() {
        this.mapperMap = new HashMap<>();
        this.actionListenerMap = new HashMap<>();
        this.builderMap = new HashMap<>();
        this.binderMap = new HashMap<>();
        this.actionListenerBind2ButtonBinderMap = new HashMap<>();
        this.keyboardActionBind2ComponentBinderMap = new HashMap<>();
    }

    public Map<JComponent, String[]> getKeyboardActionBind2ComponentBinderMap() {
        return keyboardActionBind2ComponentBinderMap;
    }

    public Map<String, EButton> getActionListenerBind2ButtonBinderMap() {
        return actionListenerBind2ButtonBinderMap;
    }

    public Map<String, EBuilder<?>> getBuilderMap() {
        return builderMap;
    }

    public Map<String, EBinder<?, ?>> getBinderMap() {
        return binderMap;
    }

    public Map<String, ActionListener> getActionListenerMap() {
        return actionListenerMap;
    }

    public Map<String, ActionMapper> getMapperMap() {
        return mapperMap;
    }

    public static class ActionMapper {
        private final String name;
        private final ActionListener action;
        private final KeyStroke aKeyStroke;
        private final int aCondition;

        public ActionMapper(String name, ActionListener action, KeyStroke keyStroke, int whenFocused) {
            this.name = name;
            this.action = action;
            this.aKeyStroke = keyStroke;
            this.aCondition = whenFocused;
        }

        public ActionMapper(String name, ActionListener anAction, KeyStroke aKeyStroke) {
            this(name, anAction, aKeyStroke, JComponent.WHEN_FOCUSED);
        }

        public String getName() {
            return name;
        }

        public ActionListener getAction() {
            return action;
        }

        public KeyStroke getaKeyStroke() {
            return aKeyStroke;
        }

        public int getaCondition() {
            return aCondition;
        }
    }
}
