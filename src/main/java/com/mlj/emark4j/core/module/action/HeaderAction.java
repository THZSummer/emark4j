package com.mlj.emark4j.core.module.action;

import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.bean.StyleScope;
import com.mlj.emark4j.core.module.ActionFactory;
import com.mlj.emark4j.core.module.DocumentManager;
import com.mlj.emark4j.core.module.EActionListener;
import com.mlj.emark4j.core.module.style.StyleManager;
import com.mlj.emark4j.core.util.DocumentUtil;
import com.mlj.emark4j.view.ViewManager;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Objects;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class HeaderAction implements EActionListener {
    private final String name;

    private final StyleScope styleScope;

    public HeaderAction(String name) {
        this.name = name;
        this.styleScope = StyleScope.PARAGRAPH;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ActionFactory.isEmark()) {
            emarkModeAction(e);
        } else {
            sourceModeAction(e);
        }
    }

    private void emarkModeAction(ActionEvent e) {
        JTextPane textPane = ViewManager.getEmark4JView().getTextPane();
        DefaultStyledDocument markDocument = DocumentManager.getMarkDocument();
        Map<String, StyleManager.StyleMapper> styleMapperMap = ActionFactory.getInstance().getStyleManager().getMapperMap();
        Style style = styleMapperMap.get(Constants.DEFAULT_STYLE_NAME).getStyle();

        boolean containsAttributes = textPane.getParagraphAttributes().containsAttributes(style);
        style = containsAttributes ? styleMapperMap.get(name).getStyle() : style;
        switch (styleScope) {
            case PARAGRAPH:
                markDocument.setParagraphAttributes(DocumentUtil.getParagraphStart(), DocumentUtil.getParagraphEnd() - DocumentUtil.getParagraphStart(), style, true);
                break;
            case CHARACTER:
                markDocument.setCharacterAttributes(DocumentUtil.getSelectionStart(), DocumentUtil.getSelectionStart() - DocumentUtil.getSelectionEnd(), style, true);
                break;
            default:
                markDocument.setLogicalStyle(textPane.getCaretPosition(), style);
                break;
        }
    }

    private void sourceModeAction(ActionEvent e) {
        JTextPane textPane = ViewManager.getEmark4JView().getTextPane();
        int paragraphStart = DocumentUtil.getParagraphStart();
        int paragraphEnd = DocumentUtil.getParagraphEnd();
        int selectionStart = DocumentUtil.getSelectionStart();
        int selectionEnd = DocumentUtil.getSelectionEnd();
        DefaultStyledDocument sourceDocument = DocumentManager.getSourceDocument();
        StyleManager.StyleMapper styleMapper = ActionFactory.getInstance().getStyleManager().getMapperMap().get(this.name);
        try {
            String text = textPane.getText(paragraphStart, paragraphEnd - paragraphStart);
            if (!Objects.isNull(text)) {
                String[] split = text.split(styleMapper.getPattern());
                if (split.length == 2) {
                    sourceDocument.remove(paragraphStart, text.length() - split[1].length());
                } else {
                    sourceDocument.insertString(paragraphStart, "#", null);
                }
            }
            System.out.println("[" + paragraphStart + ", " + paragraphEnd + "] = [" + textPane.getText(paragraphStart, paragraphEnd - paragraphStart) + "]");
            System.out.println("[" + selectionStart + ", " + selectionEnd + "] = [" + textPane.getText(selectionStart, selectionEnd - selectionStart) + "]");
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
}
