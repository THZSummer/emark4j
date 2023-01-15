package com.mlj.emark4j.module.action.register;

import com.mlj.emark4j.annotation.EActionRegisterPath;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.module.EActionRegister;
import com.mlj.emark4j.module.action.OpenFileAction;
import com.mlj.emark4j.module.action.SaveFileAction;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

public class FileActionRegister implements EActionRegister {

    @EActionRegisterPath(paths = Constants.Path.File.OPEN)
    public OpenFileAction openFileActionRegister() {
        return new OpenFileAction();
    }

    @EActionRegisterPath(paths = Constants.Path.File.SAVE)
    public SaveFileAction saveFileActionRegister() {
        return new SaveFileAction();
    }
}
