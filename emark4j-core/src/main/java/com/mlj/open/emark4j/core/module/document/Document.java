package com.mlj.open.emark4j.core.module.document;

/**
 * Document
 *
 * @author : Summe
 * @since : 2024/8/31 031
 */
public interface Document {

    String read(long line);

    void write(long line, String doc);

    DocumentIterator iterator();

    interface DocumentIterator {
        boolean hasNextLine();

        boolean nextLine();
    }
}
