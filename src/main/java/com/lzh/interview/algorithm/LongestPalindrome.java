package com.lzh.interview.algorithm;

/**
 * 最长回文子串
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
