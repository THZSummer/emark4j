package com.mlj.emark4j.module.action;

import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.bean.StyleScope;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class HeaderAction implements ActionListener {
    private final String name;

    private JTextPane textPane;

    private final StyledDocument styledDocument;

    private final StyleScope styleScope;

    private final int aCondition = JComponent.WHEN_FOCUSED;

    public HeaderAction(String name, JTextPane textPane, StyledDocument styledDocument) {
        this.name = name;
        this.textPane = textPane;
        this.styledDocument = styledDocument;
        this.styleScope = StyleScope.PARAGRAPH;
    }

    public void injectTextPane(JTextPane textPane) {
        this.textPane = textPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Style style = styledDocument.getStyle(Constants.DEFAULT_STYLE_NAME);
        boolean containsAttributes = textPane.getParagraphAttributes().containsAttributes(style);
        style = containsAttributes ? styledDocument.getStyle(name) : style;
        switch (styleScope) {
            case PARAGRAPH:
                styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), style, true);
                break;
            case CHARACTER:
                styledDocument.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), style, true);
                break;
            default:
                styledDocument.setLogicalStyle(textPane.getCaretPosition(), style);
                break;
        }
    }

    public int getaCondition() {
        return aCondition;
    }
}
