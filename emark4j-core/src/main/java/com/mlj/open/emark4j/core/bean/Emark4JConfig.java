package com.mlj.open.emark4j.core.bean;

import com.google.gson.JsonObject;
import com.mlj.open.emark4j.core.util.JsonUtil;

import java.io.InputStream;

/**
 * 2022/08/20：
 *
 * @author MLJ
 */
public class Emark4JConfig {
    private final JsonObject jsonObject;

    public Emark4JConfig(InputStream configStream) {
        this.jsonObject = JsonUtil.read(configStream);
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

}
