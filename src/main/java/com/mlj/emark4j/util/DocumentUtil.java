package com.mlj.emark4j.util;

import com.mlj.emark4j.module.ActionFactory;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * 2022/10/03：
 *
 * @author MLJ
 */
public class DocumentUtil {

    public static int getParagraphStart() {
        JTextPane textPane = ActionFactory.getInstance().getView().getTextPane();
        int len = 1000;
        for (int end = textPane.getSelectionStart(); ; end = end - len) {
            try {
                int start = Math.max(end - len, 0);
                int lastIndexOf = textPane.getStyledDocument().getText(start, end - start).lastIndexOf('\n');
                if (-1 == lastIndexOf) {
                    if (start > 0) {
                        continue;
                    } else {
                        return 0;
                    }
                }
                return start + lastIndexOf + 1;
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getParagraphEnd() {
        JTextPane textPane = ActionFactory.getInstance().getView().getTextPane();
        int len = 1000;
        for (int start = textPane.getSelectionEnd(); ; start = start + len) {
            try {
                int end = start + len;
                int indexOf = textPane.getStyledDocument().getText(start, Math.min(end, textPane.getStyledDocument().getLength()) - start).indexOf('\n');
                if (-1 == indexOf) {
                    if (end < textPane.getStyledDocument().getLength()) {
                        continue;
                    } else {
                        return textPane.getStyledDocument().getLength();
                    }
                }
                return start + indexOf;
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getSelectionStart() {
        return ActionFactory.getInstance().getView().getTextPane().getSelectionStart();
    }

    public static int getSelectionEnd() {
        return ActionFactory.getInstance().getView().getTextPane().getSelectionEnd();
    }
}
