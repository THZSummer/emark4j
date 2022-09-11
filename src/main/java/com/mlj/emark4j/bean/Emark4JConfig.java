package com.mlj.emark4j.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

/**
 * 2022/08/20：
 *
 * @author MLJ
 */
public class Emark4JConfig {
    private JSONObject jsonObject;

    public Emark4JConfig(InputStream configStream) {
        try (Scanner scanner = new Scanner(configStream)) {

            StringBuilder stringBuffer = new StringBuilder();
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                stringBuffer.append(nextLine);
            }
            scanner.close();
            this.jsonObject = JSON.parseObject(String.valueOf(stringBuffer));
            System.out.println(jsonObject.toJSONString());
        } catch (Exception exception) {
            System.err.println("init config error " + exception.getMessage());
        }
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
