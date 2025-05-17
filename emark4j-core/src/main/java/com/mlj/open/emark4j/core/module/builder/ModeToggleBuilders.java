package com.mlj.open.emark4j.core.module.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mlj.open.emark4j.core.module.ActionEBuilder;
import com.mlj.open.emark4j.core.module.ActionFactory;
import com.mlj.open.emark4j.core.module.ActionMapper;

import javax.swing.*;
import java.awt.event.InputEvent;

/**
 * 2022/09/30：
 *
 * @author MLJ
 */
public class ModeToggleBuilders {

    public static class DocModeToggleEBuilder implements ActionEBuilder {

        @Override
        public ActionMapper builder(JsonObject jsonObject) {
            String path = jsonObject.get("id").getAsString();
            JsonArray keymaps = jsonObject.getAsJsonArray("keymaps");

//        KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "^keymap$");
            KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyStroke.getKeyStroke('/').getKeyChar(), InputEvent.CTRL_DOWN_MASK);

            ActionMapper actionMapper = new ActionMapper(path, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }

    public static class EditModeToggleEBuilder implements ActionEBuilder {

        @Override
        public ActionMapper builder(JsonObject jsonObject) {
            String path = jsonObject.get("id").getAsString();
            JsonArray keymaps = jsonObject.getAsJsonArray("keymaps");

//        KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "^keymap$");
            KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyStroke.getKeyStroke('/').getKeyChar(), InputEvent.CTRL_DOWN_MASK);

            ActionMapper actionMapper = new ActionMapper(path, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }
}
