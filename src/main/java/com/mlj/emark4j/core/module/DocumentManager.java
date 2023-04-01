package com.mlj.emark4j.core.module;

import com.mlj.emark4j.core.Emark4JWare;
import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.bean.EditMode;

import javax.swing.filechooser.FileSystemView;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class DocumentManager implements Emark4JWare {
    private static final Map<String, DocumentManager> documentManagerMap = new HashMap<>();
    private static DocumentManager nowDocumentManager;

    private final String name;
    private final Map<Long, String> documentLines;
    private final DefaultStyledDocument markDocument;
    private final DefaultStyledDocument sourceDocument;
    private final StringBuffer documentBuffer;
    private URI documentURI;
    private EditMode editMode;


    private DocumentManager(URI uri) {
        this.name = uri.getPath();
        this.documentLines = new HashMap<>();
        this.markDocument = new DefaultStyledDocument();
        this.sourceDocument = new DefaultStyledDocument();
        this.documentBuffer = new StringBuffer();
        this.documentURI = uri;
        this.editMode = Constants.DEFAULT_DOC_MODE;

        documentManagerMap.put(name, this);
        nowDocumentManager = this;
    }

    public static DocumentManager createNewDocumentManager(URI uri) {
        return new DocumentManager(uri);
    }

    public static DocumentManager createNewDocumentManager() {
        return createNewDocumentManager(getUntitledUri());
    }

    private static URI getUntitledUri() {
        return new File(FileSystemView.getFileSystemView().getHomeDirectory(),
                "Untitled-" + (documentManagerMap.size() + 1) + ".emk").toURI();
    }

    @Override
    public void openDocument(URI uri) {
        new DocumentManager(uri);
    }

    @Override
    public void updateDocument(long lineNum, String line) {
        documentLines.put(lineNum, line);
        DefaultStyledDocument document = getEditMode().isEmark() ? getMarkDocument() : getSourceDocument();
        try {
            document.insertString(document.getLength(), line + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeDocument(URI uri) {
        documentManagerMap.remove(uri.getPath());
    }

    @Override
    public void switchDocument(URI uri) {
        nowDocumentManager = documentManagerMap.get(uri.getPath());
    }

    @Override
    public void saveDocument(URI uri) {

    }

    @Override
    public void renameDocumentTo(String newName) {
        String old = getName();
        documentManagerMap.put(newName, documentManagerMap.get(old));
        documentManagerMap.remove(old);
    }

    public static String getName() {
        return nowDocumentManager.name;
    }

    public static DefaultStyledDocument getMarkDocument() {
        return nowDocumentManager.markDocument;
    }

    public static DefaultStyledDocument getSourceDocument() {
        return nowDocumentManager.sourceDocument;
    }

    public static StringBuffer getDocumentBuffer() {
        return nowDocumentManager.documentBuffer;
    }


    public static URI getDocumentURI() {
        return nowDocumentManager.documentURI;
    }

    public static void setDocumentURI(URI documentURI) {
        nowDocumentManager.documentURI = documentURI;
    }

    public static EditMode getEditMode() {
        return nowDocumentManager.editMode;
    }

    public static void setEditMode(EditMode editMode) {
        nowDocumentManager.editMode = editMode;
    }
}
