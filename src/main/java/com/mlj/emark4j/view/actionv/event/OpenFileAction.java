package com.mlj.emark4j.view.actionv.event;

import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.module.ActionFactory;
import com.mlj.emark4j.core.module.EActionListener;
import com.mlj.emark4j.view.ViewManager;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URI;

/**
 * 2022/09/24：
 *
 * @author MLJ
 */

public class OpenFileAction implements EActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".emk");
            }

            @Override
            public String getDescription() {
                return "emark file";
            }
        });
        jFileChooser.showOpenDialog(ViewManager.getEmark4JView().getDesktopPane());

        readFile(jFileChooser);
    }

    private void readFile(JFileChooser jFileChooser) {
        File selectedFile = jFileChooser.getSelectedFile();
        if (null == selectedFile || !selectedFile.exists()) {
            return;
        }
        URI uri = selectedFile.toURI();

        ActionFactory.getInstance().openDocument(uri);
        ActionFactory.getInstance().getActionCenter().invokeAction(Constants.Path.File.OPEN);
    }
}
