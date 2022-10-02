package com.mlj.emark4j.module;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 2022/09/17：
 *
 * @author MLJ
 */
public class ActionManager {

    List<ActionMapper> mappers;

    public ActionManager() {
        this.mappers = new ArrayList<>();
    }

    public List<ActionMapper> getMappers() {
        return mappers;
    }

    public void setMappers(List<ActionMapper> mappers) {
        this.mappers = mappers;
    }

    public static class ActionMapper {
        ActionListener anAction;
        KeyStroke aKeyStroke;
        int aCondition;

        public ActionMapper(ActionListener action, KeyStroke keyStroke, int whenFocused) {
            this.anAction = action;
            this.aKeyStroke = keyStroke;
            this.aCondition = whenFocused;
        }

        public ActionMapper(ActionListener anAction, KeyStroke aKeyStroke) {
            this(anAction, aKeyStroke, JComponent.WHEN_FOCUSED);
        }
    }
}
