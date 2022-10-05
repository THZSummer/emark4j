package com.mlj.emark4j.module.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.annotation.ModuleBuilder;
import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.module.ActionManager;
import com.mlj.emark4j.module.action.OpenFileAction;
import com.mlj.emark4j.module.action.SaveFileAction;

import javax.swing.*;

/**
 * 2022/09/24：
 *
 * @author MLJ
 */
public class FileBuilder {

    @ModuleBuilder(pattern = "^action_file_open+$")
    public void buildOpenFile(JSONObject jsonObject) throws UnsupportedOperationException {
        String name = jsonObject.getString("name");
        JSONArray keymaps = jsonObject.getJSONArray("keymaps");

        KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "^keymap$");

        ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(name, new OpenFileAction(), keyStroke);

        ActionFactory.getInstance().addActionMapper(actionMapper);
    }

    @ModuleBuilder(pattern = "^action_file_save+$")
    public void buildSaveFile(JSONObject jsonObject) throws UnsupportedOperationException {
        String name = jsonObject.getString("name");
        JSONArray keymaps = jsonObject.getJSONArray("keymaps");

        KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "^keymap$");

        ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(name, new SaveFileAction(), keyStroke);

        ActionFactory.getInstance().addActionMapper(actionMapper);
    }
}
