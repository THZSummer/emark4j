package com.mlj.open.emark4j.core.module.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mlj.open.emark4j.core.bean.Font;
import com.mlj.open.emark4j.core.module.ElementEBuilder;

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
        public Color builder(JsonObject jsonObject) {
            JsonArray jsonArray = jsonObject.getAsJsonArray("rgba");
            return new Color(jsonArray.get(0).getAsInt(), jsonArray.get(1).getAsInt(), jsonArray.get(2).getAsInt(), jsonArray.get(3).getAsInt());
        }
    }

    public static class KeyMapEBuilder implements ElementEBuilder<KeyStroke> {

        @Override
        public KeyStroke builder(JsonObject jsonObject) {
            JsonArray jsonArray = jsonObject.getAsJsonArray("keys");
            return KeyStroke.getKeyStroke(jsonArray.get(0).getAsString() + " " + jsonArray.get(1).getAsString());
        }
    }

    public static class FontEBuilder implements ElementEBuilder<com.mlj.open.emark4j.core.bean.Font> {

        @Override
        public com.mlj.open.emark4j.core.bean.Font builder(JsonObject jsonObject) {
            com.mlj.open.emark4j.core.bean.Font font = new Font();
            String fontFamily = jsonObject.get("fontFamily").getAsString();
            Integer fontSize = jsonObject.get("fontSize").getAsInt();
            if (null != fontFamily) font.setFontFamily(fontFamily);
            if (null != fontSize) font.setFontSize(fontSize);
            return font;
        }
    }
}
