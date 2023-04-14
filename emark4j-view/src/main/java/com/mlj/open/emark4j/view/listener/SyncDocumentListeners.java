package com.mlj.open.emark4j.view.listener;

import com.mlj.open.emark4j.view.DocumentManagerV;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * 2022/10/03：
 *
 * @author MLJ
 */
public abstract class SyncDocumentListeners {

    private static void processInsertUpdate(DocumentEvent e, Document document) {
        try {
            int offset = e.getOffset();
            document.insertString(offset, e.getDocument().getText(offset, e.getLength()), null);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private static void processRemoveUpdate(DocumentEvent e, Document document) {
        try {
            document.remove(e.getOffset(), e.getLength());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private static void processChangeUpdate(DocumentEvent e, Document document) {
        int offset = e.getOffset();
        int length = e.getLength();
        try {
            String text = e.getDocument().getText(offset, length);
            document.remove(offset, length);
            document.insertString(offset, text, null);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public static class SourceDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            if (DocumentManagerV.getEditMode().isSource()) {
                processInsertUpdate(e, DocumentManagerV.getMarkDocument());
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (DocumentManagerV.getEditMode().isSource()) {
                processRemoveUpdate(e, DocumentManagerV.getMarkDocument());
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (DocumentManagerV.getEditMode().isSource()) {
                processChangeUpdate(e, DocumentManagerV.getMarkDocument());
            }
        }
    }

    public static class EmarkDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (DocumentManagerV.getEditMode().isEmark()) {
                processInsertUpdate(e, DocumentManagerV.getSourceDocument());
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (DocumentManagerV.getEditMode().isEmark()) {
                processRemoveUpdate(e, DocumentManagerV.getSourceDocument());
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (DocumentManagerV.getEditMode().isEmark()) {
                processChangeUpdate(e, DocumentManagerV.getSourceDocument());
            }
        }
    }
}
