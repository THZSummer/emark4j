package com.mlj.emark4j.module.listener;

import com.mlj.emark4j.module.ActionFactory;

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
            if (ActionFactory.getInstance().getDocumentManager().getEditMode().isSource()) {
                processInsertUpdate(e, ActionFactory.getInstance().getDocumentManager().getMarkDocument());
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (ActionFactory.getInstance().getDocumentManager().getEditMode().isSource()) {
                processRemoveUpdate(e, ActionFactory.getInstance().getDocumentManager().getMarkDocument());
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (ActionFactory.getInstance().getDocumentManager().getEditMode().isSource()) {
                processChangeUpdate(e, ActionFactory.getInstance().getDocumentManager().getMarkDocument());
            }
        }
    }

    public static class EmarkDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (ActionFactory.getInstance().getDocumentManager().getEditMode().isEmark()) {
                processInsertUpdate(e, ActionFactory.getInstance().getDocumentManager().getSourceDocument());
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (ActionFactory.getInstance().getDocumentManager().getEditMode().isEmark()) {
                processRemoveUpdate(e, ActionFactory.getInstance().getDocumentManager().getSourceDocument());
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (ActionFactory.getInstance().getDocumentManager().getEditMode().isEmark()) {
                processChangeUpdate(e, ActionFactory.getInstance().getDocumentManager().getSourceDocument());
            }
        }
    }
}
