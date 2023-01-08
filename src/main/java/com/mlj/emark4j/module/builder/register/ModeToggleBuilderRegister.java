package com.mlj.emark4j.module.builder.register;

import com.mlj.emark4j.annotation.EBuilderRegister;
import com.mlj.emark4j.module.builder.ModeToggleBuilder;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

@EBuilderRegister(path = "/editor/mode")
public class ModeToggleBuilderRegister {

    @EBuilderRegister(path = "/doc")
    public ModeToggleBuilder.DocModeToggleBuilder docModeToggleBuilderRegister() {
        return new ModeToggleBuilder.DocModeToggleBuilder();
    }

    @EBuilderRegister(path = "/edit")
    public ModeToggleBuilder.EditModeToggleBuilderA editModeToggleBuilderARegister() {
        return new ModeToggleBuilder.EditModeToggleBuilderA();
    }
}
