package com.mlj.open.emark4j.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * 2022/09/10：
 *
 * @author MLJ
 */
public class JsonUtil {

    public static JSONObject read(InputStream configStream) {
        try (Scanner scanner = new Scanner(configStream)) {

            StringBuilder stringBuffer = new StringBuilder();
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                stringBuffer.append(nextLine);
            }
            return JSON.parseObject(String.valueOf(stringBuffer));
        } catch (Exception exception) {
            System.err.println("init config error " + exception.getMessage());
        } finally {
            try {
                configStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
