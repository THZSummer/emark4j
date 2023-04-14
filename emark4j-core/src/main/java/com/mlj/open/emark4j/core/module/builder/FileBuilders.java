package com.mlj.open.emark4j.core.module.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.open.emark4j.core.module.ActionEBuilder;
import com.mlj.open.emark4j.core.module.ActionFactory;
import com.mlj.open.emark4j.core.module.ActionMapper;

import javax.swing.*;

/**
 * 2022/09/24：
 *
 * @author MLJ
 */
public class FileBuilders {

    public static class FileOpenEBuilder implements ActionEBuilder {

        @Override
        public ActionMapper builder(JSONObject jsonObject) {
            String path = jsonObject.getString("id");
            JSONArray keymaps = jsonObject.getJSONArray("keymaps");

            KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "/element/keymap");

            ActionMapper actionMapper = new ActionMapper(path, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }

    public static class FileSaveEBuilder implements ActionEBuilder {

        @Override
        public ActionMapper builder(JSONObject jsonObject) {
            String path = jsonObject.getString("id");
            JSONArray keymaps = jsonObject.getJSONArray("keymaps");

            KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "/element/keymap");

            ActionMapper actionMapper = new ActionMapper(path, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }
}
