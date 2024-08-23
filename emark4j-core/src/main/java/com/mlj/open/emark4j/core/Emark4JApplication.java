package com.mlj.open.emark4j.core;

import com.mlj.open.emark4j.core.annotation.action.EActionCorePackage;
import com.mlj.open.emark4j.core.annotation.action.EActionPackage;
import com.mlj.open.emark4j.core.annotation.action.EActionRegisterPackage;
import com.mlj.open.emark4j.core.annotation.builder.EBuilderRegisterPackage;
import com.mlj.open.emark4j.core.bean.Emark4JConfig;
import com.mlj.open.emark4j.core.module.ActionFactory;
import com.mlj.open.emark4j.core.module.DocumentManager;

import java.io.InputStream;

/**
 * 2023/04/01：
 *
 * @author MLJ
 */
@EActionPackage(packages = "com.mlj.open.emark4j.view.actionv.view")
@EActionCorePackage(packages = "com.mlj.open.emark4j.core.module.documentaction")
@EBuilderRegisterPackage(packages = "com.mlj.open.emark4j.core.module.builder.register")
@EActionRegisterPackage(packages = "com.mlj.open.emark4j.view.actionv.event.register")
public class Emark4JApplication {

    public static final InputStream CONFIG = Emark4JApplication.class.getClassLoader().getResourceAsStream("emark4j.json");

    // 1.启动
    public static void run(Class<?> primarySource, String... args) {
        // 2.读取配置文件
        Emark4JConfig emark4JConfig = new Emark4JConfig(CONFIG);

        // 3.创建DocumentManager
        DocumentManager documentManager = DocumentManager.createNewDocumentManager();
        // 4.初始化配置
        ActionFactory actionFactory = ActionFactory.getInstance();
        actionFactory.init(emark4JConfig, documentManager);
        actionFactory.initBase(Emark4JApplication.class, args);

        // 3.1.构建事件
//        actionFactory.builder();

        // 显示窗口
//        emark4JView.show();

        // 5.注册事件
//        actionFactory.registerAction();

        // 6.解析文件
        // 7.展示文件
        // 8.激活文件到可编辑
        // 9.保存文件
    }
}
