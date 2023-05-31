package com.lzh.interview.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-31
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }
}
