package com.mlj.emark4j.module.action.register;

import com.mlj.emark4j.annotation.EActionRegister;
import com.mlj.emark4j.module.action.OpenFileAction;
import com.mlj.emark4j.module.action.SaveFileAction;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

@EActionRegister(path = "/file")
public class FileActionRegister {

    @EActionRegister(path = "/open")
    public OpenFileAction openFileActionRegister() {
        return new OpenFileAction();
    }

    @EActionRegister(path = "/save")
    public SaveFileAction saveFileActionRegister() {
        return new SaveFileAction();
    }
}
