package com.mlj.open.emark4j.core.module;

import com.alibaba.fastjson.JSONObject;

/**
 * 2022/10/01：
 *
 * @author MLJ
 */
public interface EBuilder<R> {

    R builder(JSONObject jsonObject);
}
