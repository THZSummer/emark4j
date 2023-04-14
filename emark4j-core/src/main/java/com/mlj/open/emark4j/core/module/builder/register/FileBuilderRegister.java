package com.mlj.open.emark4j.core.module.builder.register;

import com.mlj.open.emark4j.core.annotation.EBuilderRegisterPath;
import com.mlj.open.emark4j.core.bean.Constants;
import com.mlj.open.emark4j.core.module.EBuilderRegister;
import com.mlj.open.emark4j.core.module.builder.FileBuilders;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

public class FileBuilderRegister implements EBuilderRegister {

    @EBuilderRegisterPath(paths = Constants.Path.File.SAVE)
    public FileBuilders.FileSaveEBuilder fileSaveBuilderRegister() {
        return new FileBuilders.FileSaveEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.File.OPEN)
    public FileBuilders.FileOpenEBuilder fileOpenBuilderRegister() {
        return new FileBuilders.FileOpenEBuilder();
    }
}
