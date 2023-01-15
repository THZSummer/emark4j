package com.mlj.emark4j.module.action;

import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.module.DocumentManager;
import com.mlj.emark4j.module.EActionListener;
import com.mlj.emark4j.view.Emark4JView;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
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
        jFileChooser.showOpenDialog(ActionFactory.getInstance().getView().getFrame());

        readFile(jFileChooser);
    }

    private void readFile(JFileChooser jFileChooser) {
        File selectedFile = jFileChooser.getSelectedFile();
        if (null == selectedFile || !selectedFile.exists()) {
            return;
        }
        ActionFactory.getInstance().openNewTab(selectedFile.toURI());
        Emark4JView emark4JView = ActionFactory.getInstance().getView();

        DocumentManager documentManager = ActionFactory.getInstance().getDocumentManager();
        documentManager.setDocumentURI(selectedFile.toURI());
        emark4JView.getFrame().setTitle("Emark4J - " + selectedFile.getPath());

        DefaultStyledDocument document = documentManager.getEditMode().isEmark() ? documentManager.getMarkDocument() : documentManager.getSourceDocument();
        new Thread(() -> {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(selectedFile, "rws");
                 FileChannel channel = randomAccessFile.getChannel()) {
                document.remove(0, document.getLength());

                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
                int read;
                Charset charset = StandardCharsets.UTF_8;
                while (-1 != (read = channel.read(byteBuffer))) {
                    byteBuffer.flip();
                    char[] array = charset.decode(byteBuffer).array();
                    String line = new String(array, 0, read);
                    document.insertString(document.getLength(), line, null);
                    byteBuffer.clear();
                    TimeUnit.MILLISECONDS.sleep(1000);
                }

            } catch (IOException | InterruptedException | BadLocationException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
