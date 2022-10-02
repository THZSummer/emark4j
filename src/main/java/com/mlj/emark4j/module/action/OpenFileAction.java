package com.mlj.emark4j.module.action;

import com.mlj.emark4j.module.ActionFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 2022/09/24：
 *
 * @author MLJ
 */

public class OpenFileAction implements ActionListener {

    private Queue<StringBuilder> queue = new ConcurrentLinkedQueue<>();
    private boolean flag = false;

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
        ActionFactory.getInstance().getDocumentManager().setDocumentURI(selectedFile.toURI());

        JTextPane textPane = ActionFactory.getInstance().getView().getTextPane();
        EditorKit editorKit = textPane.getEditorKit();
        Document document = textPane.getDocument();
        new Thread(() -> {
            try (RandomAccessFile randomAccessFile = new RandomAccessFile(selectedFile, "rws");
                 FileChannel channel = randomAccessFile.getChannel()) {
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
                int read;
                Charset charset = StandardCharsets.UTF_8;
                while (-1 != (read = channel.read(byteBuffer))) {
                    byteBuffer.flip();
                    char[] array = charset.decode(byteBuffer).array();
                    textPane.getDocument().insertString(textPane.getDocument().getLength(), new String(array, 0, read), null);
//                    textPane.setText(new String(array, 0, read));
//                    editorKit.read(new CharArrayReader(array, 0, read), document, document.getLength());
                    byteBuffer.clear();
                    Thread.sleep(1000);
                }

            } catch (IOException | InterruptedException | BadLocationException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
}
