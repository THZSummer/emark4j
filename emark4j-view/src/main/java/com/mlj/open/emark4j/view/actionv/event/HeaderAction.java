package com.mlj.open.emark4j.view.actionv.event;

import com.mlj.open.emark4j.core.module.ActionFactory;
import com.mlj.open.emark4j.core.module.DocumentManager;
import com.mlj.open.emark4j.core.module.EActionListener;
import com.mlj.open.emark4j.view.util.DocumentUtil;

import java.awt.event.ActionEvent;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class HeaderAction implements EActionListener {
    private final String name;

    public HeaderAction(String name) {
        this.name = name;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DocumentManager documentManager = ActionFactory.getInstance().getDocumentManager();
        documentManager.setSelectStart((long) DocumentUtil.getSelectionStart());
        documentManager.setSelectEnd((long) DocumentUtil.getSelectionEnd());
        ActionFactory.getInstance().getActionCenter().invokeAction(name);
    }

}
