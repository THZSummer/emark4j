package com.mlj.emark4j.view.actionv.event.register;

import com.mlj.emark4j.core.annotation.EActionRegisterPath;
import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.module.EActionRegister;
import com.mlj.emark4j.view.actionv.event.HeaderAction;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

public class HeaderActionRegister implements EActionRegister {

    @EActionRegisterPath(paths = Constants.Path.Header.HEADER0)
    public HeaderAction header0ActionRegister() {
        return new HeaderAction(Constants.Path.Header.HEADER0);
    }

    @EActionRegisterPath(paths = Constants.Path.Header.HEADER1)
    public HeaderAction header1ActionRegister() {
        return new HeaderAction(Constants.Path.Header.HEADER1);
    }

    @EActionRegisterPath(paths = Constants.Path.Header.HEADER2)
    public HeaderAction header2ActionRegister() {
        return new HeaderAction(Constants.Path.Header.HEADER2);
    }

    @EActionRegisterPath(paths = Constants.Path.Header.HEADER3)
    public HeaderAction header3ActionRegister() {
        return new HeaderAction(Constants.Path.Header.HEADER3);
    }

    @EActionRegisterPath(paths = Constants.Path.Header.HEADER4)
    public HeaderAction header4ActionRegister() {
        return new HeaderAction(Constants.Path.Header.HEADER4);
    }

    @EActionRegisterPath(paths = Constants.Path.Header.HEADER5)
    public HeaderAction header5ActionRegister() {
        return new HeaderAction(Constants.Path.Header.HEADER5);
    }

    @EActionRegisterPath(paths = Constants.Path.Header.HEADER6)
    public HeaderAction header6ActionRegister() {
        return new HeaderAction(Constants.Path.Header.HEADER6);
    }
}
