package com.mlj.emark4j.module;

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
    private final Map<String, Builder<?>> builderMap;
    private final Map<String, ActionMapper> mapperMap;

    public ActionManager() {
        this.mapperMap = new HashMap<>();
        this.actionListenerMap = new HashMap<>();
        this.builderMap = new HashMap<>();
    }

    public Map<String, Builder<?>> getBuilderMap() {
        return builderMap;
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
