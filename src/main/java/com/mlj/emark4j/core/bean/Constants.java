package com.mlj.emark4j.core.bean;

/**
 * 2022/09/24：
 *
 * @author MLJ
 */
public final class Constants {
    public static final String DEFAULT_STYLE_NAME = Path.Header.HEADER0;

    public static final EditMode DEFAULT_DOC_MODE = EditMode.WRITE_SOURCE;

    public static final class Path {

        public static final class File {

            // TODO: @Value("{file.open.path}")
            public static final String OPEN = "/file/open";
            public static final String SAVE = "/file/save";
        }

        public static final class Editor {
            public static final String DOC_MODE = "/editor/mode/doc";
            public static final String EDIT_MODE = "/editor/mode/edit";
        }

        public static final class Header {
            public static final String HEADER0 = "/header/header0";
            public static final String HEADER1 = "/header/header1";
            public static final String HEADER2 = "/header/header2";
            public static final String HEADER3 = "/header/header3";
            public static final String HEADER4 = "/header/header4";
            public static final String HEADER5 = "/header/header5";
            public static final String HEADER6 = "/header/header6";
        }

        public static final class Element {
            public static final String COLOR = "/element/color";
            public static final String FONT = "/element/font";
            public static final String KEYMAP = "/element/keymap";
        }
    }

}
