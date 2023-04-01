package com.mlj.emark4j.view.binder.register;

import com.mlj.emark4j.core.annotation.EBinderRegisterPath;
import com.mlj.emark4j.core.module.EBinderRegister;
import com.mlj.emark4j.view.binder.KeyboardActionBind2ComponentBinder;

/**
 * 2023/01/14：
 *
 * @author MLJ
 */
public class TextPaneBinderRegister implements EBinderRegister {

    @EBinderRegisterPath(paths = "/*")
    public KeyboardActionBind2ComponentBinder textPaneBinderRegister() {
        return new KeyboardActionBind2ComponentBinder();
    }
}
