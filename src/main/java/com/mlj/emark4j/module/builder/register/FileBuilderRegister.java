package com.mlj.emark4j.module.builder.register;

import com.mlj.emark4j.annotation.EBuilderRegister;
import com.mlj.emark4j.module.builder.FileBuilders;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

@EBuilderRegister(path = "/file")
public class FileBuilderRegister {

    @EBuilderRegister(path = "/save")
    public FileBuilders.FileSaveBuilder fileSaveBuilderRegister() {
        return new FileBuilders.FileSaveBuilder();
    }

    @EBuilderRegister(path = "/open")
    public FileBuilders.FileOpenBuilder fileOpenBuilderRegister() {
        return new FileBuilders.FileOpenBuilder();
    }
}
