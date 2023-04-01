package com.mlj.emark4j.core;

import com.mlj.emark4j.core.bean.Emark4JConfig;
import com.mlj.emark4j.core.module.ActionFactory;
import com.mlj.emark4j.core.module.DocumentManager;

import java.io.InputStream;

/**
 * 2023/04/01：
 *
 * @author MLJ
 */
public class Emark4JApplication {

    public static final InputStream CONFIG = Emark4JApplication.class.getClassLoader().getResourceAsStream("emark4j.json");

    // 1.启动
    public static void run(Class<?> primarySource, String... args) {
        // 2.读取配置文件
        Emark4JConfig emark4JConfig = new Emark4JConfig(CONFIG);

        // 4.初始化界面
//        Emark4JView emark4JView = new Emark4JView();

        DocumentManager documentManager = DocumentManager.createNewDocumentManager();
        // 3.初始化配置
        ActionFactory actionFactory = ActionFactory.getInstance();
        actionFactory.init(emark4JConfig, documentManager);

        // 3.1.构建事件
        actionFactory.builder();

        // 显示窗口
//        emark4JView.show();

        // 5.注册事件
//        actionFactory.registerAction();

        // 6.解析文件
        // 7.展示文件
        // 8.激活文件到可编辑
        // 9.保存文件
    }
    // 10.关闭
}
