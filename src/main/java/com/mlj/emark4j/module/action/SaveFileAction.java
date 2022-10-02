package com.mlj.emark4j.module.action;

import com.mlj.emark4j.module.ActionFactory;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 2022/09/24：
 *
 * @author MLJ
 */
public class SaveFileAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextPane textPane = ActionFactory.getInstance().getView().getTextPane();
        EditorKit editorKit = ActionFactory.getInstance().getView().getTextPane().getEditorKit();
        String path = ActionFactory.getInstance().getDocumentManager().getDocumentURI().getPath();

        try {
            int length = textPane.getDocument().getLength();
            editorKit.write(new PrintWriter(new FileOutputStream(path)), textPane.getDocument(), 0, length);
        } catch (IOException | BadLocationException ex) {
            ex.printStackTrace();
        }
    }
}
