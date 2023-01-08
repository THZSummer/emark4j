package com.mlj.emark4j.module;

import com.alibaba.fastjson.JSONObject;

/**
 * 2022/10/01：
 *
 * @author MLJ
 */
public interface Builder<R> {

    R builder(JSONObject jsonObject);
}
