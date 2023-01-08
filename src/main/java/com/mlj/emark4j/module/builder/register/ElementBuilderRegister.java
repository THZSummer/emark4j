package com.mlj.emark4j.module.builder.register;

import com.mlj.emark4j.annotation.EBuilderRegister;
import com.mlj.emark4j.module.builder.HeaderBuilders;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

@EBuilderRegister(path = "/element")
public class ElementBuilderRegister {

    @EBuilderRegister(path = "/color")
    public HeaderBuilders.ColorBuilder colorBuilderRegister() {
        return new HeaderBuilders.ColorBuilder();
    }

    @EBuilderRegister(path = "/font")
    public HeaderBuilders.FontBuilder fontBuilderRegister() {
        return new HeaderBuilders.FontBuilder();
    }

    @EBuilderRegister(path = "/keymap")
    public HeaderBuilders.KeyMapBuilder keyMapBuilderRegister() {
        return new HeaderBuilders.KeyMapBuilder();
    }
}
