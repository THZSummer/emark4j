package com.mlj.open.emark4j.core.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 2022/09/10：
 *
 * @author MLJ
 */
public class JsonUtil {

    public static JsonObject read(InputStream configStream) {
        try {
            return JsonParser.parseReader(new InputStreamReader(configStream)).getAsJsonObject();
        } catch (Exception exception) {
            System.err.println("init config error " + exception.getMessage());
        }
        return null;
    }
}
