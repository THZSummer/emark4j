package com.mlj.open.emark4j;

import com.mlj.open.emark4j.core.Emark4JApplication;
import com.mlj.open.emark4j.core.annotation.binder.EBindPackage;
import com.mlj.open.emark4j.core.annotation.binder.EBinderRegisterPackage;
import com.mlj.open.emark4j.core.module.ActionFactory;
import com.mlj.open.emark4j.view.ViewManager;

/**
 * 2022/08/20：行为驱动式文档编辑，
 *
 * @author MLJ
 */
@EBinderRegisterPackage(packages = "com.mlj.open.emark4j.view.binder.register")
@EBindPackage(packages = "com.mlj.open.emark4j.view.binder.bind")
public class Emark4J {

    // 1.启动
    public static void main(String[] args) {
        // 2.服务启动
        Emark4JApplication.run(Emark4J.class, args);
        // 3.初始化界面

        ViewManager.run(Emark4J.class, args);

        ActionFactory.setEmark4JWare(ViewManager.getInstance());
        ActionFactory.getInstance().builder();
        ViewManager.getInstance().initView(Emark4J.class, args);

        // 5.注册事件
        ViewManager.registerAction();

        ViewManager.getEmark4JView().show();
    }
}
