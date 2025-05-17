package com.mlj.open.emark4j.core.api;

import com.mlj.open.emark4j.core.module.document.Document;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class SimpleDocumentCenter implements DocumentCenter {
    private final SecureRandom secureRandom = new SecureRandom();
    private final Map<Long, Document> documentMap = new HashMap<>();
    private Document nowDocument;

    @Override
    public long addDocument(Document document) {
        long l = nextDocNum();
        documentMap.put(l, document);
        return l;
    }

    private long nextDocNum() {
        return secureRandom.nextLong();
    }

    @Override
    public Document getDocument(long docNum) {
        return documentMap.get(docNum);
    }
}
