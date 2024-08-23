package com.mlj.open.emark4j.core.module.builder.register;

import com.mlj.open.emark4j.core.annotation.builder.EBuilderRegisterPath;
import com.mlj.open.emark4j.core.bean.Constants;
import com.mlj.open.emark4j.core.module.EBuilderRegister;
import com.mlj.open.emark4j.core.module.builder.ElementBuilders;

/**
 * 2023/01/08：
 *
 * @author MLJ
 */

public class ElementBuilderRegister implements EBuilderRegister {

    @EBuilderRegisterPath(paths = Constants.Path.Element.COLOR)
    public ElementBuilders.ColorEBuilder colorBuilderRegister() {
        return new ElementBuilders.ColorEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.Element.FONT)
    public ElementBuilders.FontEBuilder fontBuilderRegister() {
        return new ElementBuilders.FontEBuilder();
    }

    @EBuilderRegisterPath(paths = Constants.Path.Element.KEYMAP)
    public ElementBuilders.KeyMapEBuilder keyMapBuilderRegister() {
        return new ElementBuilders.KeyMapEBuilder();
    }
}
