package com.mlj.open.emark4j.document;

import com.mlj.open.emark4j.core.module.document.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * SimpleDocument
 *
 * @author : Summe
 * @since : 2024/8/31 031
 */
public class SimpleDocument implements Document {

    private final Map<Long, String> mapContainer;

    public SimpleDocument() {
        this.mapContainer = new HashMap<>();
    }

    @Override
    public String read(long line) {
        return this.mapContainer.get(line);
    }

    @Override
    public void write(long line, String doc) {
        this.mapContainer.put(line, doc);
    }

    @Override
    public DocumentIterator iterator() {
        return new SimpleDocumentIterator(this.mapContainer);
    }

    private static class SimpleDocumentIterator implements DocumentIterator {

        private Map<Long, String> mapContainer;

        private long next = 1L;

        public SimpleDocumentIterator() {
        }

        public SimpleDocumentIterator(Map<Long, String> mapContainer) {
            this.mapContainer = mapContainer;
        }

        @Override
        public boolean hasNextLine() {
            return mapContainer.containsKey(next);
        }

        @Override
        public boolean nextLine() {
            return mapContainer.containsKey(++next);
        }
    }
}
