package com.mlj.emark4j.core.module;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * 2023/04/04：
 *
 * @author MLJ
 */
public class ActionMapper {
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
