package com.mlj.emark4j.module.action.register;

import com.mlj.emark4j.annotation.EActionRegister;
import com.mlj.emark4j.module.action.HeaderAction;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

@EActionRegister(path = "/header")
public class HeaderActionRegister {

    @EActionRegister(path = "/header0")
    public HeaderAction header0ActionRegister() {
        return new HeaderAction("Header0");
    }

    @EActionRegister(path = "/header1")
    public HeaderAction header1ActionRegister() {
        return new HeaderAction("Header1");
    }

    @EActionRegister(path = "/header2")
    public HeaderAction header2ActionRegister() {
        return new HeaderAction("Header2");
    }

    @EActionRegister(path = "/header3")
    public HeaderAction header3ActionRegister() {
        return new HeaderAction("Header3");
    }

    @EActionRegister(path = "/header4")
    public HeaderAction header4ActionRegister() {
        return new HeaderAction("Header4");
    }

    @EActionRegister(path = "/header5")
    public HeaderAction header5ActionRegister() {
        return new HeaderAction("Header5");
    }

    @EActionRegister(path = "/header6")
    public HeaderAction header6ActionRegister() {
        return new HeaderAction("Header6");
    }
}
