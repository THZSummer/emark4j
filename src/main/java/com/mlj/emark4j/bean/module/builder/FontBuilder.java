package com.mlj.emark4j.bean.module.builder;

import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.bean.module.Module;

import javax.swing.text.StyledDocument;


/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class FontBuilder implements Module {
    private static final FontBuilder FONT_BUILDER;
    private Font font;

    static {
        FONT_BUILDER = new FontBuilder();
    }

    @Override
    public FontBuilder builder(StyledDocument styledDocument, JSONObject jsonObject) throws UnsupportedOperationException {
        String fontFamily = jsonObject.getString("fontFamily");
        Integer fontSize = jsonObject.getInteger("fontSize");
        if (null != fontFamily) this.font.setFontFamily(fontFamily);
        if (null != fontSize) this.font.setFontSize(fontSize);
        return this;
    }

    public static Font builderFont(StyledDocument styledDocument, JSONObject jsonObject) {
        FONT_BUILDER.font = new Font();
        FONT_BUILDER.builder(styledDocument, jsonObject);
        return FONT_BUILDER.font;
    }

    static class Font {
        String fontFamily = "微软雅黑";
        int fontSize = 16;

        public String getFontFamily() {
            return fontFamily;
        }

        public void setFontFamily(String fontFamily) {
            this.fontFamily = fontFamily;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }
    }
}
