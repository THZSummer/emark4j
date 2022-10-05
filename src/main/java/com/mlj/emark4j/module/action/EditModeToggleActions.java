package com.mlj.emark4j.module.action;

import com.mlj.emark4j.bean.EditMode;
import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.module.DocumentManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 2022/09/30：
 *
 * @author MLJ
 */
public class EditModeToggleActions {

    public static class DocModeToggleAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextPane textPane = ActionFactory.getInstance().getView().getTextPane();
            DocumentManager documentManager = ActionFactory.getInstance().getDocumentManager();
            documentManager.setEditMode(documentManager.getEditMode().toggleDocMode());
            EditMode editMode = documentManager.getEditMode();
            if (editMode.isEmark()) {
                textPane.setStyledDocument(documentManager.getMarkDocument());
            } else {
                textPane.setStyledDocument(documentManager.getSourceDocument());
            }
            ActionFactory.getInstance().getView().getDocModeToggleButton().setText(editMode.name());
        }
    }

    public static class ReadonlyModeToggleAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextPane textPane = ActionFactory.getInstance().getView().getTextPane();
            DocumentManager documentManager = ActionFactory.getInstance().getDocumentManager();
            documentManager.setEditMode(documentManager.getEditMode().toggleReadonlyMode());
            EditMode editMode = documentManager.getEditMode();
            String text = "Readonly[false]";
            if (editMode.isRead()) {
                text = "Readonly[true]";
                textPane.setEnabled(false);
            } else {
                textPane.setEnabled(true);
            }
            ActionFactory.getInstance().getView().getReadonlyModeToggleButton().setText(text);
            ActionFactory.getInstance().getView().getDocModeToggleButton().setText(editMode.name());
        }
    }
}
