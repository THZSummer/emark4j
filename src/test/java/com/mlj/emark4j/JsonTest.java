package com.mlj.emark4j;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * 2022/08/20：
 *
 * @author MLJ
 */
public class JsonTest {

    public static final URL CONFIG = JsonTest.class.getResource("emark4j.json");

    public static void main(String[] args) {
        jsonTest();
    }

    public static void jsonTest() {
        try {
            assert CONFIG != null;
            try (InputStream configStream = CONFIG.openStream()) {

                Scanner scanner = new Scanner(configStream);
                StringBuilder stringBuffer = new StringBuilder();
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    stringBuffer.append(nextLine);
                }
                scanner.close();
                JSONObject jsonObject = JSON.parseObject(String.valueOf(stringBuffer));
                System.out.println(jsonObject.toJSONString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }
}
