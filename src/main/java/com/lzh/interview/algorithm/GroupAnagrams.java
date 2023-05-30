package com.lzh.interview.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;

/**
 * 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * https://leetcode.cn/problems/group-anagrams/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-30
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] list = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(JSONObject.toJSONString(groupAnagrams(list)));
    }

    private static ArrayList<ArrayList<String>> groupAnagrams(String[] list) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String word : list) {
            String sort = sort(word);
            if (!map.containsKey(sort)) {
                map.put(sort, new ArrayList<>());
            }
            map.get(sort).add(word);
        }
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (Entry<String, ArrayList<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private static String sort(String word) {
        char[] a = word.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
