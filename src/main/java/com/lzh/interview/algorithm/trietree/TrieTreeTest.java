package com.lzh.interview.algorithm.trietree;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-21
 */
public class TrieTreeTest {
    private static Set<String> s = new HashSet<>();

    static {
        s.add("12345");
        s.add("12245");
        s.add("12355");
        s.add("123456");
    }

    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.init(s);
        System.out.println(JSONObject.toJSONString(tree));
        System.out.println(tree.find("12"));
        System.out.println(tree.find("12345"));
        System.out.println(tree.find("12333"));
        System.out.println(tree.find("12455"));
        System.out.println(tree.find("111"));
        System.out.println(tree.find("1234567"));
        System.out.println(tree.find("123456"));
        System.out.println(tree.find("123"));
    }
}
