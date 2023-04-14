package com.mlj.emark4j.core.util;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.net.URI;

/**
 * 2023/04/04：
 *
 * @author MLJ
 */
public class FileUtil {

    public static URI getUntitledUri(int i) {
        return new File(FileSystemView.getFileSystemView().getHomeDirectory(),
                "Untitled-" + i + ".emk").toURI();
    }
}
