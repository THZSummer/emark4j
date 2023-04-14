package com.mlj.emark4j.core;

import java.net.URI;

/**
 * 2023/04/01：
 *
 * @author MLJ
 */
public interface Emark4JWare {

    void openDocument(URI uri);

    void updateDocument(long lineNum, String line);

    String readDocument(long lineNum);

    void closeDocument(URI uri);

    void switchDocument(URI uri);

    void saveDocument(URI uri);

    void renameDocumentTo(String newName);
}
