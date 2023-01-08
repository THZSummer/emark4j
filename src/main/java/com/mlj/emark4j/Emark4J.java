package com.mlj.emark4j;

import com.mlj.emark4j.bean.Emark4JConfig;
import com.mlj.emark4j.module.ActionFactory;
import com.mlj.emark4j.module.DocumentManager;
import com.mlj.emark4j.view.Emark4JView;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.InputStream;
import java.net.URI;

/**
 * 2022/08/20：行为驱动式文档编辑，
 *
 * @author MLJ
 */
public class Emark4J {
    public static final InputStream CONFIG = Emark4J.class.getClassLoader().getResourceAsStream("emark4j.json");

    // 1.启动
    public static void main(String[] args) {
        // 2.读取配置文件
        Emark4JConfig emark4JConfig = new Emark4JConfig(CONFIG);

        // 4.初始化界面
        Emark4JView emark4JView = new Emark4JView();

        URI uri = new File(FileSystemView.getFileSystemView().getHomeDirectory(), emark4JView.getTabTitle()).toURI();
        DocumentManager documentManager = DocumentManager.createNewDocumentManager(uri);
        // 3.初始化配置
        ActionFactory actionFactory = ActionFactory.getInstance();
        try {
            actionFactory.init(emark4JConfig, emark4JView, documentManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3.1.构建事件
        actionFactory.builder();

        // 显示窗口
        emark4JView.show();

        // 5.注册事件
        actionFactory.registerAction();

        // 6.解析文件
        // 7.展示文件
        // 8.激活文件到可编辑
        // 9.保存文件
    }
    // 10.关闭
}
