package com.mlj.emark4j.bean;

/**
 * 2022/09/30：
 *
 * @author MLJ
 */
public enum EditMode {
    READ_SOURCE("READ_SOURCE", 1, 1),
    READ_EMARK("READ_EMARK", 1, 2),
    WRITE_SOURCE("WRITE_SOURCE", 2, 1),
    WRITE_EMARK("WRITE_EMARK", 2, 2);

    private final String name;
    private final int edit;
    private final int doc;

    private EditMode(String name, int edit, int doc) {
        this.name = name;
        this.edit = edit;
        this.doc = doc;
    }

    private static EditMode parse(int edit, int doc) {
        if (edit == 1) {
            return doc == 1 ? READ_SOURCE : READ_EMARK;
        } else {
            return doc == 1 ? WRITE_SOURCE : WRITE_EMARK;
        }
    }

    public EditMode toggleDocMode() {
        return parse(this.edit, this.doc - 1);
    }

    public EditMode toggleReadonlyMode() {
        return parse(this.edit - 1, this.doc);
    }


    public boolean isRead() {
        return this.edit == 1;
    }

    public boolean isWrite() {
        return this.edit == 2;
    }

    public boolean isSource() {
        return this.doc == 1;
    }

    public boolean isEmark() {
        return this.doc == 2;
    }
}
