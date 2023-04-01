package com.mlj.emark4j.view;

import com.mlj.emark4j.core.Emark4JWare;
import com.mlj.emark4j.core.bean.EditMode;
import com.mlj.emark4j.core.module.DocumentManager;
import com.mlj.emark4j.core.module.listener.TabbedSwitchListeners;

import java.net.URI;
import java.text.MessageFormat;

/**
 * 2023/04/01：
 *
 * @author MLJ
 */
public class ViewManager implements Emark4JWare {
    private static final ViewManager DEFAULT_VIEW_MANAGER;
    private static final Emark4JView emark4JView;

    static {
        DEFAULT_VIEW_MANAGER = new ViewManager();
        emark4JView = new Emark4JView();
    }

    public static void run(Class<?> primarySource, String... args) {
        emark4JView.createNewTabbed(DocumentManager.getDocumentURI().getPath());
    }

    public static ViewManager getInstance() {
        return DEFAULT_VIEW_MANAGER;
    }

    public static Emark4JView getEmark4JView() {
        return emark4JView;
    }

    @Override
    public void openDocument(URI uri) {
        emark4JView.getFrame().setTitle("Emark4J - " + uri.getPath());
        emark4JView.createNewTabbed(uri.getPath());
        emark4JView.getTabbedPane().setSelectedIndex(emark4JView.getTabbedPane().getTabCount() - 1);
    }

    @Override
    public void updateDocument(long lineNum, String line) {
        System.out.println(MessageFormat.format("{0} : [{1}]", lineNum, line));
    }

    @Override
    public void closeDocument(URI uri) {

    }

    @Override
    public void switchDocument(URI uri) {
        refreshTabStatus();
    }

    @Override
    public void saveDocument(URI uri) {

    }

    @Override
    public void renameDocumentTo(String newName) {
        emark4JView.renameTabbed(newName);
    }

    public static void registerTabbedSwitchListener() {
        emark4JView.getTabbedPane().addChangeListener(new TabbedSwitchListeners());
    }

    private static void refreshTabStatus() {
        refreshFrameStatus();
        refreshLeftTreeStatus();
        refreshBottomToolBarStatus();
    }

    private static void refreshBottomToolBarStatus() {
        EditMode editMode = DocumentManager.getEditMode();
        emark4JView.getDocModeToggleButton().setText(editMode.name());
        emark4JView.getReadonlyModeToggleButton().setText(editMode.isRead() ? "Readonly[true]" : "Readonly[false]");
    }

    private static void refreshLeftTreeStatus() {

    }

    private static void refreshFrameStatus() {
        emark4JView.getFrame().setTitle("Emark4J - " + DocumentManager.getDocumentURI().getPath());
    }
}
