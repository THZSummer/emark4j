package com.mlj.emark4j.core.module.action;

import com.mlj.emark4j.core.bean.EditMode;
import com.mlj.emark4j.core.module.DocumentManager;
import com.mlj.emark4j.core.module.EActionListener;
import com.mlj.emark4j.view.ViewManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 2022/09/30：
 *
 * @author MLJ
 */
public class EditModeToggleActions {

    public static class DocModeToggleAction implements EActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextPane textPane = ViewManager.getEmark4JView().getTextPane();
            DocumentManager.setEditMode(DocumentManager.getEditMode().toggleDocMode());
            EditMode editMode = DocumentManager.getEditMode();
            if (editMode.isEmark()) {
                textPane.setStyledDocument(DocumentManager.getMarkDocument());
            } else {
                textPane.setStyledDocument(DocumentManager.getSourceDocument());
            }
            ViewManager.getEmark4JView().getDocModeToggleButton().setText(editMode.name());
        }
    }

    public static class ReadonlyModeToggleAction implements EActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextPane textPane = ViewManager.getEmark4JView().getTextPane();
            DocumentManager.setEditMode(DocumentManager.getEditMode().toggleReadonlyMode());
            EditMode editMode = DocumentManager.getEditMode();
            String text = "Readonly[false]";
            if (editMode.isRead()) {
                text = "Readonly[true]";
                textPane.setEnabled(false);
            } else {
                textPane.setEnabled(true);
            }
            ViewManager.getEmark4JView().getReadonlyModeToggleButton().setText(text);
            ViewManager.getEmark4JView().getDocModeToggleButton().setText(editMode.name());
        }
    }
}
