package com.mlj.open.emark4j.view.actionv.event;

import com.mlj.open.emark4j.core.bean.EditMode;
import com.mlj.open.emark4j.core.module.EActionListener;
import com.mlj.open.emark4j.view.DocumentManagerV;
import com.mlj.open.emark4j.view.ViewManager;

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
            DocumentManagerV.setEditMode(DocumentManagerV.getEditMode().toggleDocMode());
            EditMode editMode = DocumentManagerV.getEditMode();
            if (editMode.isEmark()) {
                textPane.setStyledDocument(DocumentManagerV.getMarkDocument());
            } else {
                textPane.setStyledDocument(DocumentManagerV.getSourceDocument());
            }
            ViewManager.getEmark4JView().getDocModeToggleButton().setText(editMode.name());
        }
    }

    public static class ReadonlyModeToggleAction implements EActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextPane textPane = ViewManager.getEmark4JView().getTextPane();
            DocumentManagerV.setEditMode(DocumentManagerV.getEditMode().toggleReadonlyMode());
            EditMode editMode = DocumentManagerV.getEditMode();
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
