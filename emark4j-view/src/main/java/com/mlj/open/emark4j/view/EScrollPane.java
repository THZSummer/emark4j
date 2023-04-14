package com.mlj.open.emark4j.view;

import javax.swing.*;

/**
 * 2023/01/07：
 *
 * @author MLJ
 */
public class EScrollPane extends JScrollPane {

    public EScrollPane() {
    }

    public EScrollPane(JTextPane textPane) {
        super(textPane);
        this.setViewportView(textPane);
        this.setBounds(textPane.getBounds());
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
}
