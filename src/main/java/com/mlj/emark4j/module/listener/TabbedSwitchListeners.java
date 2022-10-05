package com.mlj.emark4j.module.listener;

import com.mlj.emark4j.bean.EditMode;
import com.mlj.emark4j.module.ActionFactory;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 2022/10/06：
 *
 * @author MLJ
 */
public class TabbedSwitchListeners implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        refreshTabStatus();
    }

    public static void refreshTabStatus() {
        refreshFrameStatus();
        refreshLeftTreeStatus();
        refreshBottomToolBarStatus();
    }

    private static void refreshBottomToolBarStatus() {
        EditMode editMode = ActionFactory.getInstance().getDocumentManager().getEditMode();
        ActionFactory.getInstance().getView().getDocModeToggleButton().setText(editMode.name());
        ActionFactory.getInstance().getView().getReadonlyModeToggleButton().setText(editMode.isRead() ? "Readonly[true]" : "Readonly[false]");
    }

    private static void refreshLeftTreeStatus() {

    }

    private static void refreshFrameStatus() {
        ActionFactory.getInstance().getView().getFrame().setTitle("Emark4J - " +
                ActionFactory.getInstance().getDocumentManager().getDocumentURI().getPath());
    }
}
