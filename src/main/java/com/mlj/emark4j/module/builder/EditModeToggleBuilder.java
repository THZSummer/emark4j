package com.mlj.emark4j.module.builder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.annotation.ModuleBuilder;
import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.module.ActionManager;
import com.mlj.emark4j.module.action.EditModeToggleActions;

import javax.swing.*;
import java.awt.event.InputEvent;

/**
 * 2022/09/30：
 *
 * @author MLJ
 */
public class EditModeToggleBuilder {

    @ModuleBuilder(pattern = "^action_doc_mode_toggle+$")
    public void buildDocModeToggle(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray keymaps = jsonObject.getJSONArray("keymaps");

//        KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "^keymap$");
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyStroke.getKeyStroke('/').getKeyChar(), InputEvent.CTRL_DOWN_MASK);

        ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(name, new EditModeToggleActions.DocModeToggleAction(), keyStroke);

        ActionFactory.getInstance().addActionMapper(actionMapper);
    }

    @ModuleBuilder(pattern = "^action_readonly_mode_toggle+$")
    public void buildEditModeToggle(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray keymaps = jsonObject.getJSONArray("keymaps");

//        KeyStroke keyStroke = ActionFactory.build(keymaps.getJSONObject(0), KeyStroke.class, "^keymap$");
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyStroke.getKeyStroke('\\').getKeyChar(), InputEvent.CTRL_DOWN_MASK);

        ActionManager.ActionMapper actionMapper = new ActionManager.ActionMapper(name, new EditModeToggleActions.ReadonlyModeToggleAction(), keyStroke);

        ActionFactory.getInstance().addActionMapper(actionMapper);
    }
}
