package com.mlj.open.emark4j.test;

import com.mlj.open.emark4j.core.Emark4JApplication;
import com.mlj.open.emark4j.core.annotation.binder.EBindPackage;
import com.mlj.open.emark4j.core.annotation.binder.EBinderRegisterPackage;
import com.mlj.open.emark4j.core.context.Emark4JContext;
import com.mlj.open.emark4j.document.SimpleDocument;

/**
 * 2022/08/20：行为驱动式文档编辑，
 *
 * @author MLJ
 */
@EBinderRegisterPackage(packages = "com.mlj.open.emark4j.view.binder.register")
@EBindPackage(packages = "com.mlj.open.emark4j.view.binder.bind")
public class Emark4JTest {

    // 1.启动
    public static void main(String[] args) {
        // 2.服务启动
        Emark4JContext emark4JContext = new Emark4JApplication().run(Emark4JTest.class, args);
        emark4JContext.getDocumentCenter().addDocument(new SimpleDocument());
        System.out.println(1);
        // 3.初始化界面

//        ViewManager.run(Emark4J.class, args);
//
//        ActionFactory.setEmark4JWare(ViewManager.getInstance());
//        ActionFactory.getInstance().builder();
//        ViewManager.getInstance().initView(Emark4J.class, args);
//
//        // 5.注册事件
//        ViewManager.registerAction();
//
//        ViewManager.getEmark4JView().show();
    }
}
