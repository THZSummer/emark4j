package com.mlj.emark4j.bean.module.builder;

import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.bean.annotation.ModuleBuilder;
import com.mlj.emark4j.bean.module.AbstractModule;

import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * 2022/09/07：
 *
 * @author MLJ
 */

@ModuleBuilder(pattern = "^header_[0-6]$")
public class HeaderModuleBuilder extends AbstractModule {

    @Override
    public AbstractModule builder(StyledDocument styledDocument, JSONObject jsonObject) throws UnsupportedOperationException {
        Integer level = jsonObject.getInteger("level");

        Style header = styledDocument.addStyle(jsonObject.getString("name"), level == 0 ? null : styledDocument.getStyle("Header0"));
        FontBuilder.Font font = FontBuilder.builderFont(styledDocument, jsonObject.getJSONObject("font"));
        StyleConstants.ParagraphConstants.setFontFamily(header, font.getFontFamily());
        StyleConstants.ParagraphConstants.setFontSize(header, font.getFontSize());

        HeaderModuleBuilder moduleBuilder = new HeaderModuleBuilder();
        moduleBuilder.setStyle(header);
        moduleBuilder.setStyleScope(StyleScope.PARAGRAPH);
        return moduleBuilder;
    }
}
