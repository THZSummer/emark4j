package com.mlj.open.emark4j.view.binder.register;

import com.mlj.open.emark4j.core.annotation.binder.EBinderRegisterPath;
import com.mlj.open.emark4j.core.bean.Constants;
import com.mlj.open.emark4j.core.module.EBinderRegister;
import com.mlj.open.emark4j.view.binder.ActionListenerBind2ButtonBinder;

/**
 * 2023/01/14：
 *
 * @author MLJ
 */
public class FileBinderRegister implements EBinderRegister {

    @EBinderRegisterPath(paths = Constants.Path.File.SAVE)
    public ActionListenerBind2ButtonBinder fileSaveBuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }

    @EBinderRegisterPath(paths = Constants.Path.File.OPEN)
    public ActionListenerBind2ButtonBinder fileOpenBuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }
}
