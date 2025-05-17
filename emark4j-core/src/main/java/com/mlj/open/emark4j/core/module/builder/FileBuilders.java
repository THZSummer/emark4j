package com.mlj.open.emark4j.core.module.builder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
        public ActionMapper builder(JsonObject jsonObject) {
            String path = jsonObject.get("id").getAsString();
            JsonArray keymaps = jsonObject.get("keymaps").getAsJsonArray();

            KeyStroke keyStroke = ActionFactory.build(keymaps.get(0).getAsJsonObject(), KeyStroke.class, "/element/keymap");

            ActionMapper actionMapper = new ActionMapper(path, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }

    public static class FileSaveEBuilder implements ActionEBuilder {

        @Override
        public ActionMapper builder(JsonObject jsonObject) {
            String path = jsonObject.get("id").getAsString();
            JsonArray keymaps = jsonObject.getAsJsonArray("keymaps");

            KeyStroke keyStroke = ActionFactory.build(keymaps.get(0).getAsJsonObject(), KeyStroke.class, "/element/keymap");

            ActionMapper actionMapper = new ActionMapper(path, ActionFactory.getInstance().getActionManager().getActionListenerMap().get(path), keyStroke);

            ActionFactory.getInstance().addActionMapper(actionMapper);

            return actionMapper;
        }
    }
}
