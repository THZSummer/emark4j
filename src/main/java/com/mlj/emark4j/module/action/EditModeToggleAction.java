package com.mlj.emark4j.module.action;

import com.mlj.emark4j.module.DocumentManager;
import com.mlj.emark4j.view.Emark4JView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 2022/09/30：
 *
 * @author MLJ
 */
public class EditModeToggleAction implements ActionListener {
    private Emark4JView emark4JView;
    private DocumentManager documentManager;

    public EditModeToggleAction(Emark4JView emark4JView, DocumentManager documentManager) {
        this.emark4JView = emark4JView;
        this.documentManager = documentManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
