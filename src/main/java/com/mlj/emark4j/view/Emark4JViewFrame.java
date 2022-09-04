package com.mlj.emark4j.view;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 2022/09/03：
 *
 * @author MLJ
 */
public class Emark4JViewFrame {

    private static final Font[] FONTS;

    static {
        FONTS = new Font[]{
                new Font("微软雅黑", Font.PLAIN, 16),
                new Font("微软雅黑", Font.BOLD, 24),
                new Font("微软雅黑", Font.BOLD, 20),
                new Font("微软雅黑", Font.BOLD, 18),
                new Font("微软雅黑", Font.BOLD, 16),
                new Font("微软雅黑", Font.BOLD, 14),
                new Font("微软雅黑", Font.BOLD, 12)
        };
    }

    private JFrame frame;    //主窗口
    private JTextPane textPane;
    private DefaultStyledDocument styledDocument;

    public Emark4JViewFrame() {
        this.frame = new JFrame();
        this.textPane = new JTextPane();
        this.styledDocument = (DefaultStyledDocument) this.textPane.getStyledDocument();
        Style header0 = this.styledDocument.addStyle("header0", null);
        Style header1 = this.styledDocument.addStyle("header1", header0);
        Style header2 = this.styledDocument.addStyle("header2", header0);
        Style header3 = this.styledDocument.addStyle("header3", header0);
        Style header4 = this.styledDocument.addStyle("header4", header0);
        Style header5 = this.styledDocument.addStyle("header5", header0);
        Style header6 = this.styledDocument.addStyle("header6", header0);

        StyleConstants.ParagraphConstants.setFontFamily(header0, FONTS[0].getFamily());
        StyleConstants.ParagraphConstants.setFontSize(header0, FONTS[0].getSize());

        StyleConstants.ParagraphConstants.setFontFamily(header1, FONTS[1].getFamily());
        StyleConstants.ParagraphConstants.setFontSize(header1, FONTS[1].getSize());

        StyleConstants.ParagraphConstants.setFontFamily(header2, FONTS[2].getFamily());
        StyleConstants.ParagraphConstants.setFontSize(header2, FONTS[2].getSize());

        StyleConstants.ParagraphConstants.setFontFamily(header3, FONTS[3].getFamily());
        StyleConstants.ParagraphConstants.setFontSize(header3, FONTS[3].getSize());

        StyleConstants.ParagraphConstants.setFontFamily(header4, FONTS[4].getFamily());
        StyleConstants.ParagraphConstants.setFontSize(header4, FONTS[4].getSize());

        StyleConstants.ParagraphConstants.setFontFamily(header5, FONTS[5].getFamily());
        StyleConstants.ParagraphConstants.setFontSize(header5, FONTS[5].getSize());

        StyleConstants.ParagraphConstants.setFontFamily(header6, FONTS[6].getFamily());
        StyleConstants.ParagraphConstants.setFontSize(header6, FONTS[6].getSize());
    }

    public void show() {
        this.frame.setTitle("Emark4J");
        this.frame.setMinimumSize(new Dimension(800, 900));
        this.frame.setAlwaysOnTop(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.textPane.setAutoscrolls(true);
        this.textPane.setBackground(new Color(193, 187, 187, 255));
        this.textPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                System.out.println("[" + e.getKeyCode() + "," + e.getKeyChar() + "]");
            }
        });

        registerKey();

        JScrollPane jScrollPane = new JScrollPane(this.textPane);
        jScrollPane.setBounds(this.textPane.getBounds());
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.frame.add(jScrollPane);
        this.frame.setVisible(true);

    }

    private void registerKey() {
        this.textPane.registerKeyboardAction(e -> styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), styledDocument.getStyle("header0"), true), "Ctrl+0", KeyStroke.getKeyStroke('0', InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        this.textPane.registerKeyboardAction(e -> styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), styledDocument.getStyle("header1"), true), "Ctrl+1", KeyStroke.getKeyStroke('1', InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        this.textPane.registerKeyboardAction(e -> styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), styledDocument.getStyle("header2"), true), "Ctrl+2", KeyStroke.getKeyStroke('2', InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        this.textPane.registerKeyboardAction(e -> styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), styledDocument.getStyle("header3"), true), "Ctrl+3", KeyStroke.getKeyStroke('3', InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        this.textPane.registerKeyboardAction(e -> styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), styledDocument.getStyle("header4"), true), "Ctrl+4", KeyStroke.getKeyStroke('4', InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        this.textPane.registerKeyboardAction(e -> styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), styledDocument.getStyle("header5"), true), "Ctrl+5", KeyStroke.getKeyStroke('5', InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        this.textPane.registerKeyboardAction(e -> styledDocument.setParagraphAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), styledDocument.getStyle("header6"), true), "Ctrl+6", KeyStroke.getKeyStroke('6', InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
    }
}
