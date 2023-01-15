package com.mlj.emark4j.module.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.module.ActionEBuilder;
import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.module.ActionManager;

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
        public ActionManager.ActionMapper builder(JSONObject jsonObject) {
            String path = jsonObject.getString("id");
            JSONArray keymaps = jsonObject.getJSONArray("keymaps");

//        KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "^keymap$");
            KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyStroke.getKeyStroke('/').getKeyChar(), InputEvent.CTRL_DOWN_MASK);

            ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(path, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }

    public static class EditModeToggleEBuilder implements ActionEBuilder {

        @Override
        public ActionManager.ActionMapper builder(JSONObject jsonObject) {
            String path = jsonObject.getString("id");
            JSONArray keymaps = jsonObject.getJSONArray("keymaps");

//        KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "^keymap$");
            KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyStroke.getKeyStroke('/').getKeyChar(), InputEvent.CTRL_DOWN_MASK);

            ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(path, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }
}
