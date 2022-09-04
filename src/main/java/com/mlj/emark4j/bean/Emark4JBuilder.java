package com.mlj.emark4j.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mlj.emark4j.Emark4J;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * 2022/09/03：
 *
 * @author MLJ
 */
public class Emark4JBuilder {

    public static final URL CONFIG = Emark4JBuilder.class.getClassLoader().getResource("emark4j.json");

    public static void main(String[] args) {
        builder();
    }

    public static Emark4J builder() {
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
        return new Emark4J();
    }
}
