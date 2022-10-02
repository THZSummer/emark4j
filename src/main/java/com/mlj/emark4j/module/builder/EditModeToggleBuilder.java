package com.mlj.emark4j.module.builder;

import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.annotation.ModuleBuilder;

/**
 * 2022/09/30：
 *
 * @author MLJ
 */
public class EditModeToggleBuilder {

    @ModuleBuilder(pattern = "^action_edit_mode_toggle+$")
    public void build(JSONObject jsonObject) {
    }
}
