package com.mlj.emark4j.module.builder;

import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.bean.Font;
import com.mlj.emark4j.module.ElementEBuilder;

import javax.swing.*;
import java.awt.*;

/**
 * 2023/01/14：
 *
 * @author MLJ
 */
public class ElementBuilders {

    public static class ColorEBuilder implements ElementEBuilder<Color> {

        @Override
        public Color builder(JSONObject jsonObject) {
            int[] rgba = jsonObject.getObject("rgba", int[].class);
            return new Color(rgba[0], rgba[1], rgba[2], rgba[3]);
        }
    }

    public static class KeyMapEBuilder implements ElementEBuilder<KeyStroke> {

        @Override
        public KeyStroke builder(JSONObject jsonObject) {
            String[] keys = jsonObject.getObject("keys", String[].class);
            return KeyStroke.getKeyStroke(keys[0] + " " + keys[1]);
        }
    }

    public static class FontEBuilder implements ElementEBuilder<Font> {

        @Override
        public com.mlj.emark4j.bean.Font builder(JSONObject jsonObject) {
            com.mlj.emark4j.bean.Font font = new Font();
            String fontFamily = jsonObject.getString("fontFamily");
            Integer fontSize = jsonObject.getInteger("fontSize");
            if (null != fontFamily) font.setFontFamily(fontFamily);
            if (null != fontSize) font.setFontSize(fontSize);
            return font;
        }
    }
}
