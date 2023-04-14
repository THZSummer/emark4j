package com.mlj.open.emark4j.view.actionv.event;

import com.mlj.open.emark4j.core.module.ActionFactory;
import com.mlj.open.emark4j.core.module.DocumentManager;
import com.mlj.open.emark4j.core.module.EActionListener;
import com.mlj.open.emark4j.view.DocumentManagerV;
import com.mlj.open.emark4j.view.ViewManager;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * 2022/09/24：
 *
 * @author MLJ
 */
public class SaveFileAction implements EActionListener {
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
            ActionFactory.getInstance().renameDocumentTo(new File(uri).getName());
        }

        int len = 4096;
        try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(uri.getPath()), StandardCharsets.UTF_8))) {
            DefaultStyledDocument sourceDocument = DocumentManagerV.getSourceDocument();
            int length = sourceDocument.getLength();
            for (int i = 0; i < length; i += len) {
                printWriter.print(new String(sourceDocument.getText(i, i + len > length ? length - i : len).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
            }
        } catch (IOException | BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public URI getURI() {
        return DocumentManager.getDocumentURI();
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
        jFileChooser.showSaveDialog(ViewManager.getEmark4JView().getDesktopPane());

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
                    DocumentManager.setDocumentURI(selectedFile.toURI());
                } else {
                    selectedFile = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            int n = JOptionPane.showConfirmDialog(
                    ViewManager.getEmark4JView().getDesktopPane(),
                    selectedFile.getPath() + "\n文件已存在，是否覆盖！",
                    "文件已存在！",
                    JOptionPane.YES_NO_OPTION);
            System.out.println(n);
            if (n == JOptionPane.YES_OPTION) {
                DocumentManager.setDocumentURI(selectedFile.toURI());
            } else {
                selectedFile = null;
            }
        }

        return selectedFile;
    }
}
