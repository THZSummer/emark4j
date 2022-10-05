package com.mlj.emark4j.module;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2022/09/17：
 *
 * @author MLJ
 */
public class ActionManager {

    List<ActionMapper> mappers;

    Map<String, ActionMapper> mapperMap;

    public ActionManager() {
        this.mappers = new ArrayList<>();
        this.mapperMap = new HashMap<>();
    }

    public List<ActionMapper> getMappers() {
        return mappers;
    }

    public void setMappers(List<ActionMapper> mappers) {
        this.mappers = mappers;
    }

    public Map<String, ActionMapper> getMapperMap() {
        return mapperMap;
    }

    public void setMapperMap(Map<String, ActionMapper> mapperMap) {
        this.mapperMap = mapperMap;
    }

    public static class ActionMapper {
        String name;
        ActionListener anAction;
        KeyStroke aKeyStroke;
        int aCondition;

        public ActionMapper(String name, ActionListener action, KeyStroke keyStroke, int whenFocused) {
            this.name = name;
            this.anAction = action;
            this.aKeyStroke = keyStroke;
            this.aCondition = whenFocused;
        }

        public ActionMapper(String name, ActionListener anAction, KeyStroke aKeyStroke) {
            this(name, anAction, aKeyStroke, JComponent.WHEN_FOCUSED);
        }
    }
}
