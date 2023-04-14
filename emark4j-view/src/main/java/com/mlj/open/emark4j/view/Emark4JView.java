package com.mlj.open.emark4j.view;

import com.mlj.open.emark4j.core.bean.Constants;

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
    private final JDesktopPane desktopPane;

    private final Map<String, JTextPane> textPaneMap;
    private final Map<String, JScrollPane> textScrollPaneMap;
    private final ETabbedPane tabbedPane;

    private final JTree leftTree;
    private final JToolBar rightToolBar;
    private final JToolBar topToolBar;
    private final JToolBar bottomToolBar;

    private final EButton docModeToggleButton;
    private final EButton readonlyModeToggleButton;
    private final EButton[] headerButtons;
    private final EButton openFileButton;
    private final EButton saveFileButton;

    public Emark4JView() {
        this.frame = new JFrame();
        this.desktopPane = new JDesktopPane();

        this.textPaneMap = new HashMap<>();
        this.textScrollPaneMap = new HashMap<>();
        this.tabbedPane = new ETabbedPane();

        this.leftTree = new JTree();
        this.rightToolBar = new JToolBar();
        this.topToolBar = new JToolBar();
        this.bottomToolBar = new JToolBar();

        this.docModeToggleButton = new EButton();
        this.readonlyModeToggleButton = new EButton();
        this.headerButtons = new EButton[]{
                new EButton("Header0"),
                new EButton("Header1"),
                new EButton("Header2"),
                new EButton("Header3"),
                new EButton("Header4"),
                new EButton("Header5"),
                new EButton("Header6"),
        };
        this.openFileButton = new EButton("ActionFileOpen");
        this.saveFileButton = new EButton("ActionFileSave");

        initFrame();

//        createNewTabbed();

        initRightToolBar();

        initBottomToolBar();

        initModeToggleButton();

        initDesktopPanel();

        this.frame.add(this.desktopPane);
    }

    private void initDesktopPanel() {
        this.desktopPane.setLayout(new BorderLayout());
        this.desktopPane.add(this.tabbedPane, BorderLayout.CENTER);
        this.desktopPane.add(this.leftTree, BorderLayout.WEST);
        this.desktopPane.add(this.rightToolBar, BorderLayout.EAST);
        this.desktopPane.add(this.topToolBar, BorderLayout.NORTH);
        this.desktopPane.add(this.bottomToolBar, BorderLayout.SOUTH);
        this.desktopPane.setBackground(Color.GREEN);
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
        for (EButton headerButton : headerButtons) {
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

    public JDesktopPane getDesktopPane() {
        return desktopPane;
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

    public EButton getDocModeToggleButton() {
        return docModeToggleButton;
    }

    public EButton getReadonlyModeToggleButton() {
        return readonlyModeToggleButton;
    }

    public EButton[] getHeaderButtons() {
        return headerButtons;
    }

    public EButton getOpenFileButton() {
        return openFileButton;
    }

    public EButton getSaveFileButton() {
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
