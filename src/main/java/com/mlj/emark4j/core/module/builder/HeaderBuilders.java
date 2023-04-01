package com.mlj.emark4j.core.module.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.bean.Font;
import com.mlj.emark4j.core.module.ActionEBuilder;
import com.mlj.emark4j.core.module.ActionFactory;
import com.mlj.emark4j.core.module.ActionManager;
import com.mlj.emark4j.core.module.DocumentManager;
import com.mlj.emark4j.core.module.style.StyleManager;

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

    public static class HeaderEBuilder implements ActionEBuilder {

        @Override
        public ActionManager.ActionMapper builder(JSONObject jsonObject) {
            String path = jsonObject.getString("id");
            String pattern = jsonObject.getString("pattern");

            DefaultStyledDocument styledDocument = DocumentManager.getMarkDocument();
            Style header = styledDocument.addStyle(path, Constants.DEFAULT_STYLE_NAME.equals(path) ? null : styledDocument.getStyle(Constants.DEFAULT_STYLE_NAME));

            com.mlj.emark4j.core.bean.Font font = ActionFactory.build(jsonObject.getJSONObject("font"), Font.class, "/element/font");
            if (null != font) {
                StyleConstants.ParagraphConstants.setFontFamily(header, font.getFontFamily());
                StyleConstants.ParagraphConstants.setFontSize(header, font.getFontSize());
                if (!Constants.DEFAULT_STYLE_NAME.equals(path)) {
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

            ActionFactory.getInstance().addStyleMapper(new StyleManager.StyleMapper(path, pattern, header));

            JSONArray keymaps = jsonObject.getJSONArray("keymaps");

            KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "/element/keymap");

            ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(path, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);
            return actionMapper;
        }
    }


}
