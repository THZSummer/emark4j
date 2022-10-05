package com.mlj.emark4j.module.action;

import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.bean.StyleScope;
import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.util.DocumentUtil;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class HeaderAction implements ActionListener {
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
        JTextPane textPane = ActionFactory.getInstance().getView().getTextPane();
        DefaultStyledDocument markDocument = ActionFactory.getInstance().getDocumentManager().getMarkDocument();
        Style style = ActionFactory.getInstance().getStyleMap().get(Constants.DEFAULT_STYLE_NAME);

        boolean containsAttributes = textPane.getParagraphAttributes().containsAttributes(style);
        style = containsAttributes ? ActionFactory.getInstance().getStyleMap().get(name) : style;
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
        JTextPane textPane = ActionFactory.getInstance().getView().getTextPane();
        int paragraphStart = DocumentUtil.getParagraphStart();
        int paragraphEnd = DocumentUtil.getParagraphEnd();
        int selectionStart = DocumentUtil.getSelectionStart();
        int selectionEnd = DocumentUtil.getSelectionEnd();
        DefaultStyledDocument sourceDocument = ActionFactory.getInstance().getDocumentManager().getSourceDocument();
        try {
            String text = textPane.getText(paragraphStart, paragraphEnd - paragraphStart);
            if (Objects.isNull(text)) {

            } else {
                String[] split = text.split("^#* *");
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
