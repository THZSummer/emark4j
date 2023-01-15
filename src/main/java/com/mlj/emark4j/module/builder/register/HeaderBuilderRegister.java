package com.mlj.emark4j.module.builder.register;

import com.mlj.emark4j.annotation.EBuilderRegisterPath;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.module.EBuilderRegister;
import com.mlj.emark4j.module.builder.HeaderBuilders;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

public class HeaderBuilderRegister implements EBuilderRegister {

    @EBuilderRegisterPath(paths = Constants.Path.Header.HEADER0)
    public HeaderBuilders.HeaderEBuilder header0BuilderRegister() {
        return new HeaderBuilders.HeaderEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.Header.HEADER1)
    public HeaderBuilders.HeaderEBuilder header1BuilderRegister() {
        return new HeaderBuilders.HeaderEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.Header.HEADER2)
    public HeaderBuilders.HeaderEBuilder header2BuilderRegister() {
        return new HeaderBuilders.HeaderEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.Header.HEADER3)
    public HeaderBuilders.HeaderEBuilder header3BuilderRegister() {
        return new HeaderBuilders.HeaderEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.Header.HEADER4)
    public HeaderBuilders.HeaderEBuilder header4BuilderRegister() {
        return new HeaderBuilders.HeaderEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.Header.HEADER5)
    public HeaderBuilders.HeaderEBuilder header5BuilderRegister() {
        return new HeaderBuilders.HeaderEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.Header.HEADER6)
    public HeaderBuilders.HeaderEBuilder header6BuilderRegister() {
        return new HeaderBuilders.HeaderEBuilder();
    }
}
