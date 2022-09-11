package com.mlj.emark4j.bean.module;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.PlainDocument;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class DocumentManager {
    private DefaultStyledDocument markDocument;
    private PlainDocument sourceDocument;

    public DocumentManager() {
        this.markDocument = new DefaultStyledDocument();
        this.sourceDocument = new PlainDocument();
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
}
