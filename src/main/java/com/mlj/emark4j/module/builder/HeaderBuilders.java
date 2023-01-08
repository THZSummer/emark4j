package com.mlj.emark4j.module.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.bean.Font;
import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.module.ActionManager;
import com.mlj.emark4j.module.Builder;
import com.mlj.emark4j.module.StyleManager;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.*;

/**
 * 2022/09/07：
 *
 * @author MLJ
 */

public class HeaderBuilders {

    public static class HeaderBuilder implements Builder<ActionManager.ActionMapper> {

        @Override
        public ActionManager.ActionMapper builder(JSONObject jsonObject) {
            String path = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            String pattern = jsonObject.getString("pattern");

            DefaultStyledDocument styledDocument = ActionFactory.getInstance().getDocumentManager().getMarkDocument();
            Style header = styledDocument.addStyle(name, Constants.DEFAULT_STYLE_NAME.equals(name) ? null : styledDocument.getStyle(Constants.DEFAULT_STYLE_NAME));

            Font font = ActionFactory.build(jsonObject.getJSONObject("font"), Font.class, "/element/font");
            if (null != font) {
                StyleConstants.ParagraphConstants.setFontFamily(header, font.getFontFamily());
                StyleConstants.ParagraphConstants.setFontSize(header, font.getFontSize());
                if (!Constants.DEFAULT_STYLE_NAME.equals(name)) {
                    StyleConstants.FontConstants.setBold(header, true);
                    SimpleAttributeSet header0 = new SimpleAttributeSet();
                    header0.addAttribute(StyleConstants.ParagraphConstants.SpaceAbove, font.getFontSize() / 2f);
                    header0.addAttribute(StyleConstants.ParagraphConstants.SpaceBelow, font.getFontSize() / 2f);
                    header0.addAttribute(StyleConstants.ParagraphConstants.LeftIndent, 0f);
                    header0.addAttribute(StyleConstants.ParagraphConstants.RightIndent, 0f);
                    header0.addAttribute(StyleConstants.ParagraphConstants.FirstLineIndent, 0f);
                    header.addAttributes(header0);
                } else {
                    SimpleAttributeSet header0 = new SimpleAttributeSet();
                    header0.addAttribute(StyleConstants.ParagraphConstants.SpaceAbove, 2f);
                    header0.addAttribute(StyleConstants.ParagraphConstants.SpaceBelow, 2f);
                    header0.addAttribute(StyleConstants.ParagraphConstants.LeftIndent, 2f);
                    header0.addAttribute(StyleConstants.ParagraphConstants.RightIndent, 2f);
                    header0.addAttribute(StyleConstants.ParagraphConstants.FirstLineIndent, font.getFontSize() * 2.0f);
                    header.addAttributes(header0);
                }
            }
            JSONObject foreColor = jsonObject.getJSONObject("foreColor");
            JSONObject backColor = jsonObject.getJSONObject("backColor");

            if (null != foreColor) {
                StyleConstants.ColorConstants.setBackground(header, ActionFactory.build(foreColor, Color.class, "/element/color"));
            }
            if (null != backColor) {
                StyleConstants.ColorConstants.setBackground(header, ActionFactory.build(backColor, Color.class, "/element/color"));
            }

            ActionFactory.getInstance().addStyleMapper(new StyleManager.StyleMapper(name, pattern, header));

            JSONArray keymaps = jsonObject.getJSONArray("keymaps");

            KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "/element/keymap");

            ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(name, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);
            return actionMapper;
        }
    }

    public static class KeyMapBuilder implements Builder<KeyStroke> {

        @Override
        public KeyStroke builder(JSONObject jsonObject) {
            String[] keys = jsonObject.getObject("keys", String[].class);
            return KeyStroke.getKeyStroke(keys[0] + " " + keys[1]);
        }
    }

    public static class FontBuilder implements Builder<Font> {

        @Override
        public Font builder(JSONObject jsonObject) {
            Font font = new Font();
            String fontFamily = jsonObject.getString("fontFamily");
            Integer fontSize = jsonObject.getInteger("fontSize");
            if (null != fontFamily) font.setFontFamily(fontFamily);
            if (null != fontSize) font.setFontSize(fontSize);
            return font;
        }
    }

    public static class ColorBuilder implements Builder<Color> {

        @Override
        public Color builder(JSONObject jsonObject) {
            int[] rgba = jsonObject.getObject("rgba", int[].class);
            return new Color(rgba[0], rgba[1], rgba[2], rgba[3]);
        }
    }
}
