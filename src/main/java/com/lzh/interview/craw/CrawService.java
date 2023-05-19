package com.lzh.interview.craw;

import java.net.URL;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.common.collect.Lists;

/**
 * @author lizhuohang <lizhuohang@kuaishou.com>
 * Created on 2020-08-07
 */
public class CrawService {

    private static final int TIMEOUT_MILLIS = 5000;

    @Nullable
    private static Document getDocument(String url) {
        try {
            URL u = new URL(url);
            return Jsoup.parse(u, TIMEOUT_MILLIS);
        } catch (Exception e) {
            return null;
        }
    }

    private static String getValue(Document document, String findPath, @Nonnull DocumentHandler handler) {
        return handler.get(document, findPath);
    }

    private static List<String> getImgList(Document document, String findPath, @Nonnull DocumentImgHandler handler) {
        return handler.getList(document, findPath);
    }

    /**
     * @param url 网页地址
     * @param findPath 元素路径，每个节点用|分隔，.表示class，#表示id，*表示标签名，不带标记表示属性
     *          此字段暂时没用
     * @param handler
     * @return
     */
    public static String getValue(String url, String findPath, @Nonnull DocumentHandler handler) {
        try {
            if (StringUtils.isBlank(url)) {
                return StringUtils.EMPTY;
            }
            Document document = getDocument(url);
            if (document == null) {
                return StringUtils.EMPTY;
            }

            return getValue(document, findPath, handler);
        } catch (Throwable throwable) {
            return StringUtils.EMPTY;
        }
    }

    public static List<String> getImgList(String url, String findPath, @Nonnull DocumentImgHandler handler) {
        try {
            if (StringUtils.isBlank(url)) {
                return Lists.newArrayList();
            }
            Document document = getDocument(url);
            if (document == null) {
                return Lists.newArrayList();
            }

            return getImgList(document, findPath, handler);
        } catch (Throwable throwable) {
            return Lists.newArrayList();
        }
    }
}
