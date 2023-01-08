package com.mlj.emark4j.module.action;

import com.mlj.emark4j.module.ActionFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

/**
 * 2022/09/24：
 *
 * @author MLJ
 */
public class SaveFileAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        URI uri = getURI();
        if (!new File(uri.getPath()).exists()) {
            File newFile = saveToNewFile();

            if (null == newFile) {
                return;
            }
            uri = getURI();

            if (null == uri) {
                return;
            }
            ActionFactory.getInstance().renameTabTo(new File(uri).getName());
        }

        int len = 4096;
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(uri.getPath()))) {
            DefaultStyledDocument sourceDocument = ActionFactory.getInstance().getDocumentManager().getSourceDocument();
            int length = sourceDocument.getLength();
            for (int i = 0; i < length; i += len) {
                printWriter.print(sourceDocument.getText(i, i + len > length ? length - i : len));
            }
        } catch (IOException | BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public URI getURI() {
        return ActionFactory.getInstance().getDocumentManager().getDocumentURI();
    }

    public File saveToNewFile() {
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
        jFileChooser.showSaveDialog(ActionFactory.getInstance().getView().getFrame());

        File selectedFile = jFileChooser.getSelectedFile();

        if (null == selectedFile) {
            return null;
        }

        if (!selectedFile.exists()) {
            if (!selectedFile.getName().endsWith(".emk")) {
                selectedFile = new File(selectedFile.getPath() + ".emk");
            }
            try {
                boolean newFile = selectedFile.createNewFile();
                if (newFile) {
                    ActionFactory.getInstance().getDocumentManager().setDocumentURI(selectedFile.toURI());
                } else {
                    selectedFile = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            int n = JOptionPane.showConfirmDialog(
                    ActionFactory.getInstance().getView().getFrame(),
                    selectedFile.getPath() + "\n文件已存在，是否覆盖！",
                    "文件已存在！",
                    JOptionPane.YES_NO_OPTION);
            System.out.println(n);
            if (n == JOptionPane.YES_OPTION) {
                ActionFactory.getInstance().getDocumentManager().setDocumentURI(selectedFile.toURI());
            } else {
                selectedFile = null;
            }
        }

        return selectedFile;
    }
}
