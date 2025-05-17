package com.mlj.open.emark4j.core.context;

import com.mlj.open.emark4j.core.api.ActionCenter;
import com.mlj.open.emark4j.core.api.DocumentCenter;

/**
 * Emark4JContext
 *
 * @author : Summe
 * @since : 2024/8/24 024
 */
public interface Emark4JContext {

    public ActionCenter getActionCenter();

    public DocumentCenter getDocumentCenter();
}
