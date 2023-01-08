package com.mlj.emark4j.module.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.module.ActionManager;
import com.mlj.emark4j.module.Builder;

import javax.swing.*;

/**
 * 2022/09/24：
 *
 * @author MLJ
 */
public class FileBuilders {

    public static class FileOpenBuilder implements Builder<ActionManager.ActionMapper> {

        @Override
        public ActionManager.ActionMapper builder(JSONObject jsonObject) {
            String path = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            JSONArray keymaps = jsonObject.getJSONArray("keymaps");

            KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "/element/keymap");

            ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(name, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }

    public static class FileSaveBuilder implements Builder<ActionManager.ActionMapper> {

        @Override
        public ActionManager.ActionMapper builder(JSONObject jsonObject) {
            String path = jsonObject.getString("id");
            String name = jsonObject.getString("name");
            JSONArray keymaps = jsonObject.getJSONArray("keymaps");

            KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "/element/keymap");

            ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(name, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }
}
