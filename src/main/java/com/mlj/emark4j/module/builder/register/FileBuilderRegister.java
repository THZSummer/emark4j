package com.mlj.emark4j.module.builder.register;

import com.mlj.emark4j.annotation.EBuilderRegisterPath;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.module.EBuilderRegister;
import com.mlj.emark4j.module.builder.FileBuilders;

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
