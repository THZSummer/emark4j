package com.mlj.emark4j.module.action.register;

import com.mlj.emark4j.annotation.EActionRegister;
import com.mlj.emark4j.module.action.EditModeToggleActions;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

@EActionRegister(path = "/editor/mode")
public class EditModeToggleActionRegister {

    @EActionRegister(path = "/doc")
    public EditModeToggleActions.DocModeToggleAction docModeToggleActionRegister() {
        return new EditModeToggleActions.DocModeToggleAction();
    }

    @EActionRegister(path = "/edit")
    public EditModeToggleActions.ReadonlyModeToggleAction readonlyModeToggleActionRegister() {
        return new EditModeToggleActions.ReadonlyModeToggleAction();
    }
}
