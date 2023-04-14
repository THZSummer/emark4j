package com.mlj.emark4j.core.module;

import javax.swing.text.StyleConstants;
import java.util.HashMap;
import java.util.Map;

/**
 * 2023/04/07：
 *
 * @author MLJ
 */
public class ElementManager {

    private static final ElementManager DEFAULT_ELEMENT_MANAGER;

    private final Map<String, String> pathMap;

    private final Map<String, StyleWrapper> styleWrapperMap;

    static {
        DEFAULT_ELEMENT_MANAGER = new ElementManager();
    }

    public static ElementManager getInstance() {
        return DEFAULT_ELEMENT_MANAGER;
    }

    private ElementManager() {
        pathMap = new HashMap<>();
        styleWrapperMap = new HashMap<>();
    }

    private class StyleWrapper {
        private StyleConstants styleConstants;
        private Object value;
    }
}
