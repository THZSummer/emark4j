package com.mlj.open.emark4j.core.module;


import com.google.gson.JsonObject;

/**
 * 2022/10/01：
 *
 * @author MLJ
 */
public interface EBuilder<R> {

    R builder(JsonObject jsonObject);
}
