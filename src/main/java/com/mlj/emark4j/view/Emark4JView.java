package com.mlj.emark4j.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 2022/09/03：
 *
 * @author MLJ
 */
public class Emark4JView {
    private final JFrame frame;    //主窗口
    private final JTextPane textPane;

    public Emark4JView() {
        this.frame = new JFrame();
        this.textPane = new JTextPane();
        this.textPane.setDoubleBuffered(true);
    }

    public void show() {
        this.frame.setTitle("Emark4J");
        this.frame.setMinimumSize(new Dimension(800, 900));
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.textPane.setAutoscrolls(true);
        this.textPane.setBackground(new Color(193, 187, 187, 255));
        this.textPane.setAutoscrolls(true);
        this.textPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println("[" + e.getKeyCode() + "," + e.getKeyChar() + "]");
            }
        });

        JScrollPane jScrollPane = new JScrollPane(this.textPane);
        jScrollPane.setBounds(this.textPane.getBounds());
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.frame.add(jScrollPane);
        this.frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextPane getTextPane() {
        return textPane;
    }
}
