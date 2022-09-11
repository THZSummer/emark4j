package com.mlj.emark4j.bean.module;

import com.alibaba.fastjson.JSONObject;

import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

/**
 * 2022/09/07：
 *
 * @author MLJ
 */
public abstract class AbstractModule implements Module {
    private Style style;

    private StyleScope styleScope;

    private int maskKeyCode;

    private int vKeyCode;

    @Override
    public AbstractModule builder(StyledDocument styledDocument, JSONObject jsonObject) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("UnsupportedOperationException");
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public StyleScope getStyleScope() {
        return styleScope;
    }

    public void setStyleScope(StyleScope styleScope) {
        this.styleScope = styleScope;
    }

    public int getMaskKeyCode() {
        return maskKeyCode;
    }

    public void setMaskKeyCode(int maskKeyCode) {
        this.maskKeyCode = maskKeyCode;
    }

    public int getvKeyCode() {
        return vKeyCode;
    }

    public void setvKeyCode(int vKeyCode) {
        this.vKeyCode = vKeyCode;
    }

    public enum StyleScope {
        PARAGRAPH, CHARACTER
    }
}
