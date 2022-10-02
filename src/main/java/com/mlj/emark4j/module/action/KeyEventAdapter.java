package com.mlj.emark4j.module.action;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 2022/09/12：
 *
 * @author MLJ
 */
public class KeyEventAdapter extends KeyEvent {
    public KeyEventAdapter(Component source, int id, long when, int modifiers, int keyCode, char keyChar, int keyLocation) {
        super(source, id, when, modifiers, keyCode, keyChar, keyLocation);
    }

    public KeyEventAdapter(Component source, int id, long when, int modifiers, int keyCode, char keyChar) {
        super(source, id, when, modifiers, keyCode, keyChar);
    }

    @Deprecated
    public KeyEventAdapter(Component source, int id, long when, int modifiers, int keyCode) {
        super(source, id, when, modifiers, keyCode);
    }
}
