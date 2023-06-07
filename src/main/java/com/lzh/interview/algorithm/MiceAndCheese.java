package com.lzh.interview.algorithm;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/mice-and-cheese/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-06-07
 */
public class MiceAndCheese {

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int sum = 0;
        int[] diff = new int[reward1.length];
        for (int i = 0; i < reward1.length; i++) {
            sum += reward2[i];
            diff[i] = reward1[i] - reward2[i];
        }
        Arrays.sort(diff);
        for (int i = 0; i < k; i++) {
            sum += diff[reward1.length - 1 - i];
        }
        return sum;
    }
}
