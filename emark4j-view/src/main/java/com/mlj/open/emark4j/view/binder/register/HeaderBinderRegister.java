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
public class HeaderBinderRegister implements EBinderRegister {

    @EBinderRegisterPath(paths = Constants.Path.Header.HEADER0)
    public ActionListenerBind2ButtonBinder header0BuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }

    @EBinderRegisterPath(paths = Constants.Path.Header.HEADER1)
    public ActionListenerBind2ButtonBinder header1BuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }

    @EBinderRegisterPath(paths = Constants.Path.Header.HEADER2)
    public ActionListenerBind2ButtonBinder header2BuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }

    @EBinderRegisterPath(paths = Constants.Path.Header.HEADER3)
    public ActionListenerBind2ButtonBinder header3BuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }

    @EBinderRegisterPath(paths = Constants.Path.Header.HEADER4)
    public ActionListenerBind2ButtonBinder header4BuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }

    @EBinderRegisterPath(paths = Constants.Path.Header.HEADER5)
    public ActionListenerBind2ButtonBinder header5BuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }

    @EBinderRegisterPath(paths = Constants.Path.Header.HEADER6)
    public ActionListenerBind2ButtonBinder header6BuilderRegister() {
        return new ActionListenerBind2ButtonBinder();
    }
}
