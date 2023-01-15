package com.mlj.emark4j.module.action.register;

import com.mlj.emark4j.annotation.EActionRegisterPath;
import com.mlj.emark4j.bean.Constants;
import com.mlj.emark4j.module.EActionRegister;
import com.mlj.emark4j.module.action.EditModeToggleActions;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

public class EditModeToggleActionRegister implements EActionRegister {

    @EActionRegisterPath(paths = Constants.Path.Editor.DOC_MODE)
    public EditModeToggleActions.DocModeToggleAction docModeToggleActionRegister() {
        return new EditModeToggleActions.DocModeToggleAction();
    }

    @EActionRegisterPath(paths = Constants.Path.Editor.EDIT_MODE)
    public EditModeToggleActions.ReadonlyModeToggleAction readonlyModeToggleActionRegister() {
        return new EditModeToggleActions.ReadonlyModeToggleAction();
    }
}
