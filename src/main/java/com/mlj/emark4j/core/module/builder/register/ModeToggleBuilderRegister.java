package com.mlj.emark4j.core.module.builder.register;

import com.mlj.emark4j.core.annotation.EBuilderRegisterPath;
import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.module.EBuilderRegister;
import com.mlj.emark4j.core.module.builder.ModeToggleBuilders;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

public class ModeToggleBuilderRegister implements EBuilderRegister {

    @EBuilderRegisterPath(paths = Constants.Path.Editor.EDIT_MODE)
    public ModeToggleBuilders.DocModeToggleEBuilder docModeToggleBuilderRegister() {
        return new ModeToggleBuilders.DocModeToggleEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.Editor.DOC_MODE)
    public ModeToggleBuilders.EditModeToggleEBuilder editModeToggleBuilderARegister() {
        return new ModeToggleBuilders.EditModeToggleEBuilder();
    }
}
