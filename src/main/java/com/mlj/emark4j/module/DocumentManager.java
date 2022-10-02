package com.mlj.emark4j.module;

import com.mlj.emark4j.bean.EditMode;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.PlainDocument;
import java.net.URI;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class DocumentManager {
    private DefaultStyledDocument markDocument;
    private PlainDocument sourceDocument;
    private StringBuffer documentBuffer;
    private URI documentURI;
    private EditMode editMode;

    public DocumentManager() {
        this.markDocument = new DefaultStyledDocument();
        this.sourceDocument = new PlainDocument();
        this.documentBuffer = new StringBuffer();
        this.editMode = EditMode.WRITE_EMARK;
    }

    public DefaultStyledDocument getMarkDocument() {
        return markDocument;
    }

    public void setMarkDocument(DefaultStyledDocument markDocument) {
        this.markDocument = markDocument;
    }

    public PlainDocument getSourceDocument() {
        return sourceDocument;
    }

    public void setSourceDocument(PlainDocument sourceDocument) {
        this.sourceDocument = sourceDocument;
    }

    public StringBuffer getDocumentBuffer() {
        return documentBuffer;
    }

    public void setDocumentBuffer(StringBuffer documentBuffer) {
        this.documentBuffer = documentBuffer;
    }

    public URI getDocumentURI() {
        return documentURI;
    }

    public void setDocumentURI(URI documentURI) {
        this.documentURI = documentURI;
    }

    public EditMode getEditMode() {
        return editMode;
    }

    public void setEditMode(EditMode editMode) {
        this.editMode = editMode;
    }
}
