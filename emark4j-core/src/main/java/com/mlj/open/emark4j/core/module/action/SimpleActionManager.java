package com.mlj.open.emark4j.core.module.action;

import com.mlj.open.emark4j.core.module.ActionMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 2023/04/05：
 *
 * @author MLJ
 */
public class SimpleActionManager extends ActionManager {

    private static final SimpleActionManager simpleActionCenter;

    static {
        simpleActionCenter = new SimpleActionManager();
    }

    private SimpleActionManager() {
    }

    public static SimpleActionManager getInstance() {
        return simpleActionCenter;
    }

    @Override
    public ActionMapper getActionMapper() {
        return null;
    }

    @Override
    public void invokeAction(String path, Object... args) {
        try {
            Method method4Document = getDocumentActionMap().get(path);
            if (null != method4Document) {
                method4Document.invoke(getDocumentActionObjMap().get(path), args);
            }

            Method method4View = getViewActionMap().get(path);
            if (null != method4View) {
                method4View.invoke(getViewActionObjMap().get(path), args);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}