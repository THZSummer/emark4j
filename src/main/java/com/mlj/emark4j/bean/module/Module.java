package com.mlj.emark4j.bean.module;

import com.alibaba.fastjson.JSONObject;

import javax.swing.text.StyledDocument;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public interface Module {
    Module builder(StyledDocument styledDocument, JSONObject jsonObject) throws UnsupportedOperationException;
}
