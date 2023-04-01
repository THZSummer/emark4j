package com.mlj.emark4j.core.module.action;

import com.mlj.emark4j.core.module.ActionFactory;
import com.mlj.emark4j.core.module.EActionListener;
import com.mlj.emark4j.view.ViewManager;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

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

        new Thread(() -> {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(selectedFile, "rws")) {
                long lineNum = 0L;
                String line;
                while (null != (line = randomAccessFile.readLine())) {
                    ActionFactory.getInstance().updateDocument(lineNum++, new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                    TimeUnit.MILLISECONDS.sleep(50);
                }

            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
