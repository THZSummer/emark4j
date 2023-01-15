package com.mlj.emark4j.view.binder;

import com.mlj.emark4j.module.ActionManager;
import com.mlj.emark4j.module.EBinder;

import javax.swing.*;

/**
 * 2023/01/14：
 *
 * @author MLJ
 */
public class KeyboardActionBind2ComponentBinder implements EBinder<ActionManager.ActionMapper, JComponent> {

    public void binder(ActionManager.ActionMapper actionMapper, JComponent component) {
        component.registerKeyboardAction(actionMapper.getAction(), actionMapper.getaKeyStroke(), actionMapper.getaCondition());
    }
}
