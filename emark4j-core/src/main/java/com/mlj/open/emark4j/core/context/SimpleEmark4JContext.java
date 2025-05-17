package com.mlj.open.emark4j.core.context;

import com.mlj.open.emark4j.core.api.ActionCenter;
import com.mlj.open.emark4j.core.api.DocumentCenter;
import com.mlj.open.emark4j.core.api.SimpleActionCenter;
import com.mlj.open.emark4j.core.api.SimpleDocumentCenter;

/**
 * SimpleEmark4jContext
 *
 * @author : Summe
 * @since : 2024/8/24 024
 */
public class SimpleEmark4JContext implements Emark4JContext {

    @Override
    public ActionCenter getActionCenter() {
        return new SimpleActionCenter();
    }

    @Override
    public DocumentCenter getDocumentCenter() {
        return new SimpleDocumentCenter();
    }
}
