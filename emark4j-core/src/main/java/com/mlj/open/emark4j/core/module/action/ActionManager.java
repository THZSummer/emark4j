package com.mlj.open.emark4j.core.module.action;

import com.mlj.open.emark4j.core.module.ActionMapper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 2023/04/04：
 *
 * @author MLJ
 */
public abstract class ActionManager {

    private final Map<String, Method> documentActionMap = new HashMap<>();
    private final Map<String, Object> documentActionObjMap = new HashMap<>();

    private final Map<String, Method> viewActionMap = new HashMap<>();
    private final Map<String, Object> viewActionObjMap = new HashMap<>();

    /**
     * 获取事件定义：触发方式等信息
     *
     * @return ActionMapper
     */
    public ActionMapper getActionMapper() {
        throw new UnsupportedOperationException();
    }

    public void invokeAction(String path, Object... args) {
        throw new UnsupportedOperationException();
    }

    public Map<String, Method> getDocumentActionMap() {
        return documentActionMap;
    }

    public Map<String, Method> getViewActionMap() {
        return viewActionMap;
    }

    public Map<String, Object> getDocumentActionObjMap() {
        return documentActionObjMap;
    }

    public Map<String, Object> getViewActionObjMap() {
        return viewActionObjMap;
    }
}
