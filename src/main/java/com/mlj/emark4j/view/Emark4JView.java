package com.mlj.emark4j.view;

import com.mlj.emark4j.bean.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022/09/03：
 *
 * @author MLJ
 */
public class Emark4JView {
    private final JFrame frame;    //主窗口

    private final Map<String, JTextPane> textPaneMap;
    private final Map<String, JScrollPane> textScrollPaneMap;
    private final ETabbedPane tabbedPane;

    private final JTree leftTree;
    private final JToolBar rightToolBar;
    private final JToolBar topToolBar;
    private final JToolBar bottomToolBar;

    private final JButton docModeToggleButton;
    private final JButton readonlyModeToggleButton;
    private final JButton[] headerButtons;
    private final JButton openFileButton;
    private final JButton saveFileButton;

    public Emark4JView() {
        this.frame = new JFrame();

        this.textPaneMap = new HashMap<>();
        this.textScrollPaneMap = new HashMap<>();
        this.tabbedPane = new ETabbedPane();

        this.leftTree = new JTree();
        this.rightToolBar = new JToolBar();
        this.topToolBar = new JToolBar();
        this.bottomToolBar = new JToolBar();

        this.docModeToggleButton = new JButton();
        this.readonlyModeToggleButton = new JButton();
        this.headerButtons = new JButton[]{
                new JButton("Header0"),
                new JButton("Header1"),
                new JButton("Header2"),
                new JButton("Header3"),
                new JButton("Header4"),
                new JButton("Header5"),
                new JButton("Header6"),
        };
        this.openFileButton = new JButton("ActionFileOpen");
        this.saveFileButton = new JButton("ActionFileSave");

        initFrame();

        createNewTabbed();

        initRightToolBar();

        initBottomToolBar();

        initModeToggleButton();

        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.tabbedPane, BorderLayout.CENTER);
        this.frame.add(this.leftTree, BorderLayout.WEST);
        this.frame.add(this.rightToolBar, BorderLayout.EAST);
        this.frame.add(this.topToolBar, BorderLayout.NORTH);
        this.frame.add(this.bottomToolBar, BorderLayout.SOUTH);
    }

    public void createNewTabbed() {
        createNewTabbed("Untitled-" + (this.tabbedPane.getTabCount() + 1) + ".emk");
    }

    public void createNewTabbed(String tabbedName) {
        ETextPane jTextPane = new ETextPane();
        EScrollPane jScrollPane = new EScrollPane(jTextPane);

        this.textPaneMap.put(tabbedName, jTextPane);
        this.textScrollPaneMap.put(tabbedName, jScrollPane);

        this.tabbedPane.addTab(tabbedName, jScrollPane);
    }

    public void renameTabbed(String newName) {
        String old = this.getTabTitle();
        this.textPaneMap.put(newName, this.textPaneMap.get(old));
        this.textScrollPaneMap.put(newName, this.textScrollPaneMap.get(old));
        this.textPaneMap.remove(old);
        this.textScrollPaneMap.remove(old);
        this.tabbedPane.setTitleAt(this.tabbedPane.getSelectedIndex(), newName);
    }

    private void initRightToolBar() {
        this.rightToolBar.setOrientation(JToolBar.VERTICAL);
        for (JButton headerButton : headerButtons) {
            this.rightToolBar.add(headerButton);
        }
        this.rightToolBar.addSeparator();
        this.rightToolBar.add(this.openFileButton);
        this.rightToolBar.add(this.saveFileButton);
    }

    private void initModeToggleButton() {
        this.docModeToggleButton.setText(Constants.DEFAULT_DOC_MODE.name());
        this.readonlyModeToggleButton.setText("Readonly[closed]");
    }

    private void initBottomToolBar() {
        this.bottomToolBar.add(this.docModeToggleButton);
        this.bottomToolBar.add(this.readonlyModeToggleButton);
    }

    private void initFrame() {
        this.frame.setTitle("Emark4J");
        this.frame.setMinimumSize(new Dimension(1000, 800));
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        this.frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public String getTabTitle() {
        return this.tabbedPane.getTitleAt(this.tabbedPane.getSelectedIndex());
    }

    public JTextPane getTextPane() {
        return this.textPaneMap.get(this.getTabTitle());
    }

    public JScrollPane getTextScrollPane() {
        return this.textScrollPaneMap.get(this.getTabTitle());
    }

    public JTree getLeftTree() {
        return leftTree;
    }

    public JToolBar getRightToolBar() {
        return rightToolBar;
    }

    public JToolBar getTopToolBar() {
        return topToolBar;
    }

    public JToolBar getBottomToolBar() {
        return bottomToolBar;
    }

    public JButton getDocModeToggleButton() {
        return docModeToggleButton;
    }

    public JButton getReadonlyModeToggleButton() {
        return readonlyModeToggleButton;
    }

    public JButton[] getHeaderButtons() {
        return headerButtons;
    }

    public JButton getOpenFileButton() {
        return openFileButton;
    }

    public JButton getSaveFileButton() {
        return saveFileButton;
    }

    public Map<String, JTextPane> getTextPaneMap() {
        return textPaneMap;
    }

    public Map<String, JScrollPane> getTextScrollPaneMap() {
        return textScrollPaneMap;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
