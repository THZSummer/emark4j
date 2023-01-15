package com.mlj.emark4j.view.binder.register;

import com.mlj.emark4j.annotation.EBinderRegisterPath;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.module.EBinderRegister;
import com.mlj.emark4j.view.binder.ActionListenerBind2ButtonBinder;

/**
 * 2023/01/14：
 *
 * @author MLJ
 */
public class ModeToggleBinderRegister implements EBinderRegister {
    @EBinderRegisterPath(paths = Constants.Path.Editor.EDIT_MODE)
    public ActionListenerBind2ButtonBinder docModeToggleBuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }

    @EBinderRegisterPath(paths = Constants.Path.Editor.DOC_MODE)
    public ActionListenerBind2ButtonBinder editModeToggleBuilderARegister() {
        return new ActionListenerBind2ButtonBinder();
    }
}
