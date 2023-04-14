package com.mlj.open.emark4j.view.actionv.event.register;

import com.mlj.open.emark4j.core.annotation.EActionRegisterPath;
import com.mlj.open.emark4j.core.bean.Constants;
import com.mlj.open.emark4j.core.module.EActionRegister;
import com.mlj.open.emark4j.view.actionv.event.OpenFileAction;
import com.mlj.open.emark4j.view.actionv.event.SaveFileAction;

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
