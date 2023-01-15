package com.mlj.emark4j.view.binder;

import com.mlj.emark4j.module.EBinder;
import com.mlj.emark4j.view.EButton;

import java.awt.event.ActionListener;

/**
 * 2023/01/14：
 *
 * @author MLJ
 */
public class ActionListenerBind2ButtonBinder implements EBinder<ActionListener, EButton> {

    public void binder(ActionListener listener, EButton button) {
        button.addActionListener(listener);
    }
}
