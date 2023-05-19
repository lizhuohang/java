package com.lzh.interview.craw;

import java.io.IOException;

import org.jsoup.nodes.Document;

/**
 * @author lizhuohang <lizhuohang@kuaishou.com>
 * Created on 2020-08-07
 */
public interface DocumentHandler {
    /**
     *
     * @param document
     * @param findPath 元素路径，每个节点用|分隔，.表示class，#表示id，*表示标签名，不带标记表示属性
     *          此字段暂时没用
     * @return
     */
    String get(Document document, String findPath);

}
