package com.lzh.interview.algorithm;

import java.util.TreeMap;

/**
 * 给你一个 下标从 0 开始 的正整数数组 w ，其中 w[i] 代表第 i 个下标的权重。
 * 请你实现一个函数 pickIndex ，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。选取下标 i 的 概率 为 w[i] / sum(w) 。
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 * https://leetcode.cn/problems/random-pick-with-weight/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-06-05
 */
public class RandomPickWithWeight {

    TreeMap<Integer, Integer> treeMap;
    int sum = 0;

    public RandomPickWithWeight(int[] w) {
        this.treeMap = new TreeMap<>();

        for (int i = 0; i < w.length; i++) {
            treeMap.put(sum, i);
            sum += w[i];
        }
    }

    public int pickIndex() {
        return treeMap.floorEntry((int) (Math.random() * sum)).getValue();
    }
}
