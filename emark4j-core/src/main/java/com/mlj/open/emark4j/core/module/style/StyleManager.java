package com.mlj.open.emark4j.core.module.style;

import javax.swing.text.Style;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022/10/06：
 *
 * @author MLJ
 */
public class StyleManager {
    private static final StyleManager STYLE_MANAGER;

    private final Map<String, StyleMapper> mapperMap;

    static {
        STYLE_MANAGER = new StyleManager();
    }

    public static StyleManager getInstance() {
        return STYLE_MANAGER;
    }

    private StyleManager() {
        this.mapperMap = new HashMap<>();
    }

    public Map<String, StyleMapper> getMapperMap() {
        return mapperMap;
    }

    public static class StyleMapper {
        private final String name;
        private final String pattern;
        private final Style style;

        public StyleMapper(String name, String pattern, Style style) {
            this.name = name;
            this.pattern = pattern;
            this.style = style;
        }

        public String getName() {
            return name;
        }

        public String getPattern() {
            return pattern;
        }

        public Style getStyle() {
            return style;
        }
    }
}
