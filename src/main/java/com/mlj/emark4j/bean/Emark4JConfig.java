package com.mlj.emark4j.bean;

import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.util.JsonUtil;

import java.io.InputStream;

/**
 * 2022/08/20：
 *
 * @author MLJ
 */
public class Emark4JConfig {
    private JSONObject jsonObject;

    public Emark4JConfig(InputStream configStream) {
        this.jsonObject = JsonUtil.read(configStream);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
