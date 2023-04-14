package com.mlj.emark4j.core.module.documentaction;

import com.mlj.emark4j.core.annotation.EActionCore;
import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.module.ActionFactory;
import com.mlj.emark4j.core.module.DocumentManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 2023/04/05：
 *
 * @author MLJ
 */
public class FileActions {

    @EActionCore(paths = Constants.Path.File.SAVE)
    public void saveFileAction() {
        DocumentManager documentManager = ActionFactory.getInstance().getDocumentManager();
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(DocumentManager.getDocumentURI().getPath()))) {
            String line;
            long lineNum = 1L;
            while (null != (line = documentManager.readDocument(lineNum++))) {
                printWriter.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @EActionCore(paths = Constants.Path.File.OPEN)
    public void openFileAction() {
        DocumentManager documentManager = ActionFactory.getInstance().getDocumentManager();
        try (Scanner printWriter = new Scanner(new FileInputStream(DocumentManager.getDocumentURI().getPath()))) {
            long lineNum = 1L;
            while (printWriter.hasNextLine()) {
                documentManager.updateDocument(lineNum++, printWriter.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
