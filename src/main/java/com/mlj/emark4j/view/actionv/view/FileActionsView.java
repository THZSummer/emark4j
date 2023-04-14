package com.mlj.emark4j.view.actionv.view;

import com.mlj.emark4j.core.annotation.EAction;
import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.module.ActionFactory;
import com.mlj.emark4j.core.module.DocumentManager;
import com.mlj.emark4j.view.DocumentManagerV;
import com.mlj.emark4j.view.ViewManager;

/**
 * 2023/04/06：
 *
 * @author MLJ
 */
public class FileActionsView {

    @EAction(paths = Constants.Path.File.SAVE)
    public void saveFileAction() {

    }

    @EAction(paths = Constants.Path.File.OPEN)
    public void openFileAction() {
        readFile();
    }

    private void readFile() {
        DocumentManager documentManager = ActionFactory.getInstance().getDocumentManager();
        DocumentManagerV documentManagerV = ViewManager.getDocumentManagerV();
        String line;
        long lineNum = 1;

        while (null != (line = documentManager.readDocument(lineNum))) {
            documentManagerV.updateDocument(lineNum++, line);
        }
    }
}
