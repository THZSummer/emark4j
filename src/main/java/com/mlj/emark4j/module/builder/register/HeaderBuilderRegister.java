package com.mlj.emark4j.module.builder.register;

import com.mlj.emark4j.annotation.EBuilderRegister;
import com.mlj.emark4j.module.builder.HeaderBuilders;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

@EBuilderRegister(path = "/header")
public class HeaderBuilderRegister {

    @EBuilderRegister(path = "/header0")
    public HeaderBuilders.HeaderBuilder header0BuilderRegister() {
        return new HeaderBuilders.HeaderBuilder();
    }

    @EBuilderRegister(path = "/header1")
    public HeaderBuilders.HeaderBuilder header1BuilderRegister() {
        return new HeaderBuilders.HeaderBuilder();
    }

    @EBuilderRegister(path = "/header2")
    public HeaderBuilders.HeaderBuilder header2BuilderRegister() {
        return new HeaderBuilders.HeaderBuilder();
    }

    @EBuilderRegister(path = "/header3")
    public HeaderBuilders.HeaderBuilder header3BuilderRegister() {
        return new HeaderBuilders.HeaderBuilder();
    }

    @EBuilderRegister(path = "/header4")
    public HeaderBuilders.HeaderBuilder header4BuilderRegister() {
        return new HeaderBuilders.HeaderBuilder();
    }

    @EBuilderRegister(path = "/header5")
    public HeaderBuilders.HeaderBuilder header5BuilderRegister() {
        return new HeaderBuilders.HeaderBuilder();
    }

    @EBuilderRegister(path = "/header6")
    public HeaderBuilders.HeaderBuilder header6BuilderRegister() {
        return new HeaderBuilders.HeaderBuilder();
    }
}
