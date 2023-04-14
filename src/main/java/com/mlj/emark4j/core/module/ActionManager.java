package com.mlj.emark4j.core.module;

import com.mlj.emark4j.core.bean.StyleScope;
import com.mlj.emark4j.view.EButton;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022/09/17：
 *
 * @author MLJ
 */
public class ActionManager {
    private static final ActionManager ACTION_MANAGER;

    private final Map<String, ActionListener> actionListenerMap;
    private final Map<String, EBuilder<?>> builderMap;
    private final Map<String, EBinder<?, ?>> binderMap;
    private final Map<String, EButton> actionListenerBind2ButtonBinderMap;
    private final Map<JComponent, String[]> keyboardActionBind2ComponentBinderMap;
    private final Map<String, ActionMapper> mapperMap;

    /**
     * style
     */
    private final Map<String, Style> styleMap;
    private final Map<String, StyleScope> styleScopeMap;

    static {
        ACTION_MANAGER = new ActionManager();
    }

    public static ActionManager getInstance() {
        return ACTION_MANAGER;
    }

    private ActionManager() {
        this.mapperMap = new HashMap<>();
        this.actionListenerMap = new HashMap<>();
        this.builderMap = new HashMap<>();
        this.binderMap = new HashMap<>();
        this.actionListenerBind2ButtonBinderMap = new HashMap<>();
        this.keyboardActionBind2ComponentBinderMap = new HashMap<>();

        this.styleMap = new HashMap<>();
        this.styleScopeMap = new HashMap<>();
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

    public Map<String, Style> getStyleMap() {
        return styleMap;
    }

    public Map<String, StyleScope> getStyleScopeMap() {
        return styleScopeMap;
    }
}
