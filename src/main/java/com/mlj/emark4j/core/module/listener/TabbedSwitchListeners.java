package com.mlj.emark4j.core.module.listener;

import com.mlj.emark4j.core.module.ActionFactory;
import com.mlj.emark4j.view.ViewManager;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;

/**
 * 2022/10/06：
 *
 * @author MLJ
 */
public class TabbedSwitchListeners implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        int selectedIndex = ViewManager.getEmark4JView().getTabbedPane().getSelectedIndex();
        String title = ViewManager.getEmark4JView().getTabbedPane().getTitleAt(selectedIndex);
        ActionFactory.getInstance().switchDocument(new File(title).toURI());
    }
}
