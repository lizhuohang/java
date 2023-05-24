package com.lzh.interview.algorithm;

/**
 * 最长回文子串
 * https://leetcode.cn/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-fa-he-dong-tai-gui-hua-by-reedfa/
 * <p>
 * 对于每一个回文子串，除去他以及跟他在一起的与他相同的字符后任然是回文子串
 * <p>
 * 以 cdiaaidc 为例
 * <p>
 * 遍历字符串，在位置第一个a处，在其左右找跟他相同的字符，然后标记left为其左边第一个不跟他相等的字符处
 * 标记right为其右边第一个不跟他相等的字符处
 * 那么left和right中间夹的这一段肯定是回文串，有几种可能 "a" "aa" "aaa" "aaaa"（其中可能当前遍历到的是第一个a或者是第二个三个a）
 * 然后left和right往外扩散，直到字符不相等
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-24
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(find("eiciaicd"));
    }

    private static String find(String s) {
        if (s == null || s.equals("")) {
            return null;
        }

        int sLen = s.length();
        int maxLen = 1;
        int left, right;
        int maxStart = 0;

        for (int i = 0; i < sLen; i++) {
            int len = 1;
            left = i - 1;
            right = i + 1;
            while (left >= 0 && s.charAt(i) == s.charAt(left)) {
                left--;
                len++;
            }
            while (right < sLen && s.charAt(i) == s.charAt(right)) {
                right++;
                len++;
            }
            while (left >= 0 && right < sLen && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                len += 2;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left + 1;
            }
        }
        return s.substring(maxStart, maxLen + maxStart);
    }
}
