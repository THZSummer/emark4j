package com.mlj.emark4j.view.actionv.view;

import com.mlj.emark4j.core.annotation.EAction;
import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.module.ActionFactory;
import com.mlj.emark4j.view.DocumentManagerV;
import com.mlj.emark4j.view.ViewManager;
import com.mlj.emark4j.view.util.DocumentUtil;

import javax.swing.*;
import javax.swing.text.*;
import java.text.MessageFormat;
import java.util.Map;

/**
 * 2023/04/05：
 *
 * @author MLJ
 */
public class HeaderActionsView {

    @EAction(paths = Constants.Path.Header.HEADER0)
    public void header0Action() {
        headerActionBase(Constants.Path.Header.HEADER0);
    }

    @EAction(paths = Constants.Path.Header.HEADER1)
    public void header1Action() {
        headerActionBase(Constants.Path.Header.HEADER1);
    }

    @EAction(paths = Constants.Path.Header.HEADER2)
    public void header2Action() {
        headerActionBase(Constants.Path.Header.HEADER2);
    }

    @EAction(paths = Constants.Path.Header.HEADER3)
    public void header3Action() {
        headerActionBase(Constants.Path.Header.HEADER3);
    }

    @EAction(paths = Constants.Path.Header.HEADER4)
    public void header4Action() {
        headerActionBase(Constants.Path.Header.HEADER4);
    }

    @EAction(paths = Constants.Path.Header.HEADER5)
    public void header5Action() {
        headerActionBase(Constants.Path.Header.HEADER5);
    }

    @EAction(paths = Constants.Path.Header.HEADER6)
    public void header6Action() {
        headerActionBase(Constants.Path.Header.HEADER6);
    }

    private void headerActionBase(String path) {
        if (DocumentManagerV.getEditMode().isEmark()) {
            emarkModeAction(path);
        } else {
            sourceModeAction(path);
        }
    }

    private void emarkModeAction(String name) {
        JTextPane textPane = ViewManager.getEmark4JView().getTextPane();
        DefaultStyledDocument markDocument = DocumentManagerV.getMarkDocument();
        Map<String, Style> styleMap = ActionFactory.getInstance().getActionManager().getStyleMap();
        Style style = styleMap.get(Constants.DEFAULT_STYLE_NAME);

        boolean containsAttributes = textPane.getParagraphAttributes().containsAttributes(style);
        style = containsAttributes ? styleMap.get(name) : style;
        switch (ActionFactory.getInstance().getActionManager().getStyleScopeMap().get(name)) {
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

    private void sourceModeAction(String name) {
        DefaultStyledDocument sourceDocument = DocumentManagerV.getSourceDocument();

        Element defaultRootElement = sourceDocument.getDefaultRootElement();

        int lineIndexStart = defaultRootElement.getElementIndex(DocumentUtil.getSelectionStart());
        int lineIndexEnd = defaultRootElement.getElementIndex(DocumentUtil.getSelectionEnd());

        for (int i = lineIndexStart; i <= lineIndexEnd; i++) {
            try {
                Element element = defaultRootElement.getElement(i);
                Document paragraphDocument = element.getDocument();
                String readDocument = ActionFactory.getInstance().getDocumentManager().readDocument(i + 1);
                System.out.println(MessageFormat.format("View=[{0}][{1}]",
                        paragraphDocument.getText(element.getStartOffset(),
                                element.getEndOffset() - element.getStartOffset()), readDocument + "\n"));
                paragraphDocument.remove(element.getStartOffset(), element.getEndOffset() - element.getStartOffset());
                paragraphDocument.insertString(element.getStartOffset(), readDocument + "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
}
