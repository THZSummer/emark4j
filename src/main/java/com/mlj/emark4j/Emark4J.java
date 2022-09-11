package com.mlj.emark4j;

import com.mlj.emark4j.bean.Emark4JConfig;
import com.mlj.emark4j.bean.module.AbstractModule;
import com.mlj.emark4j.bean.module.ActionFactory;
import com.mlj.emark4j.bean.module.DocumentManager;
import com.mlj.emark4j.view.Emark4JView;

import java.io.InputStream;
import java.util.List;

/**
 * 2022/08/20：
 *
 * @author MLJ
 */
public class Emark4J {
    public static final InputStream CONFIG = Emark4J.class.getClassLoader().getResourceAsStream("emark4j.json");

    // 1.启动
    public static void main(String[] args) {
        // 2.读取配置文件
        Emark4JConfig emark4JConfig = new Emark4JConfig(CONFIG);

        // 3.初始化配置
        ActionFactory actionFactory = ActionFactory.getInstance(emark4JConfig);

        DocumentManager documentManager = new DocumentManager();

        List<AbstractModule> builder = actionFactory.builder(documentManager.getMarkDocument());

        // 4.初始化界面
        Emark4JView emark4JView = new Emark4JView(documentManager);
        emark4JView.show();

        // 5.注册事件
        actionFactory.registerAction(emark4JView, documentManager);

        // 6.解析文件
        // 7.展示文件
        // 8.激活文件到可编辑
        // 9.保存文件
    }
    // 10.关闭
}
