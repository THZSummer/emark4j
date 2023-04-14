package com.mlj.emark4j.view.actionv.event.register;

import com.mlj.emark4j.core.annotation.EActionRegisterPath;
import com.mlj.emark4j.core.bean.Constants;
import com.mlj.emark4j.core.module.EActionRegister;
import com.mlj.emark4j.view.actionv.event.EditModeToggleActions;

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
