package com.mlj.open.emark4j.core.api;

/**
 * EActionCenter
 *
 * @author : Summe
 * @since : 2024/8/24 024
 */
public interface ActionCenter {

    void action(String path);

    void action(String path, long docNum);
}
