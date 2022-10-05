package com.mlj.emark4j.module;

import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.bean.EditMode;

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
public class DocumentManager {
    private final String name;
    private final DefaultStyledDocument markDocument;
    private final DefaultStyledDocument sourceDocument;
    private final StringBuffer documentBuffer;
    private URI documentURI;
    private EditMode editMode;

    private static final Map<String, DocumentManager> documentManagerMap = new HashMap<>();

    private DocumentManager(URI uri) {
        this.name = new File(uri.getPath()).getName();
        this.markDocument = new DefaultStyledDocument();
        this.sourceDocument = new DefaultStyledDocument();
        this.documentBuffer = new StringBuffer();
        this.documentURI = uri;
        this.editMode = Constants.DEFAULT_DOC_MODE;

        documentManagerMap.put(name, this);
    }

    public static DocumentManager createNewDocumentManager(URI uri) {
        return new DocumentManager(uri);
    }

    public void renameDocumentTo(String newName) {
        String old = this.getName();
        documentManagerMap.put(newName, documentManagerMap.get(old));
        documentManagerMap.remove(old);
    }

    private DocumentManager getTabDocumentManager() {
        return documentManagerMap.get(ActionFactory.getInstance().getView().getTabTitle());
    }

    public String getName() {
        return getTabDocumentManager().name;
    }

    public DefaultStyledDocument getMarkDocument() {
        return getTabDocumentManager().markDocument;
    }

    public DefaultStyledDocument getSourceDocument() {
        return getTabDocumentManager().sourceDocument;
    }

    public StringBuffer getDocumentBuffer() {
        return getTabDocumentManager().documentBuffer;
    }


    public URI getDocumentURI() {
        return getTabDocumentManager().documentURI;
    }

    public void setDocumentURI(URI documentURI) {
        getTabDocumentManager().documentURI = documentURI;
    }

    public EditMode getEditMode() {
        return getTabDocumentManager().editMode;
    }

    public void setEditMode(EditMode editMode) {
        getTabDocumentManager().editMode = editMode;
    }
}
