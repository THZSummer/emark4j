package com.mlj.open.emark4j.view.binder;

import com.mlj.open.emark4j.core.module.ActionMapper;
import com.mlj.open.emark4j.core.module.EBinder;

import javax.swing.*;

/**
 * 2023/01/14：
 *
 * @author MLJ
 */
public class KeyboardActionBind2ComponentBinder implements EBinder<ActionMapper, JComponent> {

    public void binder(ActionMapper actionMapper, JComponent component) {
        component.registerKeyboardAction(actionMapper.getAction(), actionMapper.getaKeyStroke(), actionMapper.getaCondition());
    }
}
