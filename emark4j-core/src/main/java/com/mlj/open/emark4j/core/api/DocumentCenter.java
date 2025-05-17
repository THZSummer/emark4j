package com.mlj.open.emark4j.core.api;

import com.mlj.open.emark4j.core.module.document.Document;

/**
 * DocumentCenter
 *
 * @author : Summe
 * @since : 2024/8/24 024
 */
public interface DocumentCenter {

    long addDocument(Document document);

    Document getDocument(long docNum);
}
