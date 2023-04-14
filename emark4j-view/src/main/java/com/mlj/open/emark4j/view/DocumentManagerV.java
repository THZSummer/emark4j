package com.mlj.open.emark4j.view;

import com.mlj.open.emark4j.core.Emark4JWare;
import com.mlj.open.emark4j.core.bean.Constants;
import com.mlj.open.emark4j.core.bean.EditMode;
import com.mlj.open.emark4j.core.util.FileUtil;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class DocumentManagerV implements Emark4JWare {
    private static final Map<String, DocumentManagerV> documentManagerMap = new HashMap<>();
    private static DocumentManagerV nowDocumentManager;

    private final String name;
    private final DefaultStyledDocument markDocument;
    private final DefaultStyledDocument sourceDocument;
    private URI documentURI;
    private EditMode editMode;


    private DocumentManagerV(URI uri) {
        this.name = uri.getPath();
        this.markDocument = new DefaultStyledDocument();
        this.sourceDocument = new DefaultStyledDocument();
        this.documentURI = uri;
        this.editMode = Constants.DEFAULT_DOC_MODE;

        documentManagerMap.put(name, this);
        nowDocumentManager = this;
    }

    public static DocumentManagerV createNewDocumentManager(URI uri) {
        return new DocumentManagerV(uri);
    }

    public static DocumentManagerV createNewDocumentManager() {
        return createNewDocumentManager(getUntitledUri());
    }

    private static URI getUntitledUri() {
        return FileUtil.getUntitledUri(documentManagerMap.size() + 1);
    }

    @Override
    public void openDocument(URI uri) {
        new DocumentManagerV(uri);
    }

    @Override
    public void updateDocument(long lineNum, String line) {
        DefaultStyledDocument document = getEditMode().isEmark() ? getMarkDocument() : getSourceDocument();
        try {
            document.insertString(document.getLength(), line + "\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readDocument(long lineNum) {
        return null;
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

    private static DocumentManagerV getNowDocumentManager() {
        System.out.println("V:" + nowDocumentManager.documentURI.getPath());
        return nowDocumentManager;
    }

    public static String getName() {
        return getNowDocumentManager().name;
    }

    public static DefaultStyledDocument getMarkDocument() {
        return getNowDocumentManager().markDocument;
    }

    public static DefaultStyledDocument getSourceDocument() {
        return getNowDocumentManager().sourceDocument;
    }


    public static URI getDocumentURI() {
        return getNowDocumentManager().documentURI;
    }

    public static void setDocumentURI(URI documentURI) {
        getNowDocumentManager().documentURI = documentURI;
    }

    public static EditMode getEditMode() {
        return getNowDocumentManager().editMode;
    }

    public static void setEditMode(EditMode editMode) {
        getNowDocumentManager().editMode = editMode;
    }
}
