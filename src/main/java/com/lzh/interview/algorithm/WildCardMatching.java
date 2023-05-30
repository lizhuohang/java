package com.lzh.interview.algorithm;

/**
 * 通配符匹配
 * 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
 * https://leetcode.cn/problems/wildcard-matching/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-30
 */
public class WildCardMatching {

    public static void main(String[] args) {
        System.out.println("-------false-------");
        System.out.println(isMatch("abcdefg", "abcdef"));
        System.out.println(isMatch("abcdefg", "abcdefgd"));
        System.out.println(isMatch("abcdefg", "aabcdefg"));
        System.out.println(isMatch("aabcdefg", "abcdefgd"));
        System.out.println(isMatch("abcdefg", "?abcdefg"));
        System.out.println(isMatch("abcdefg", "abc?defg"));
        System.out.println("-------true-------");
        System.out.println(isMatch("abcdefg", "abcdefg"));
        System.out.println(isMatch("aabcdefg", "*"));
        System.out.println(isMatch("aabcdefg", "?abcdefg"));
        System.out.println(isMatch("abcdefg", "abc?efg"));
        System.out.println(isMatch("abcdefg", "abc?efg*"));
        System.out.println(isMatch("abcdefg", "abc?e*"));

    }

    private static boolean isMatch(String str, String pattern) {
        int pIndex = 0;
        char pChar = pattern.charAt(pIndex);
        for (int i = 0; i < str.length(); i++) {
            int temp = match(str.charAt(i), pChar);
            if (temp < 0) {
                return false;
            }
            pIndex += temp;
            if (pIndex == pattern.length()) {
                if (i == str.length() - 1) {
                    return true;
                }
                return false;
            }
            pChar = pattern.charAt(pIndex);
        }
        if (pIndex <= pattern.length() - 1 && pChar != '*') {
            return false;
        }
        return true;
    }

    /**
     * @param c 待匹配字符
     * @param p 正则位
     * @return 负数：匹配不上，0：匹配，不需要移位，1：匹配，需要移位
     */
    private static int match(char c, char p) {
        if (p == '*') {
            return 0;
        }
        if (p == '?' || p == c) {
            return 1;
        }
        return -1;
    }
}
