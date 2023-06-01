package com.lzh.interview.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * https://leetcode.cn/problems/top-k-frequent-elements/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * @Date: Created in 12:01 18/3/23
 */
public class TopN {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 0);
            }
            map.put(num, map.get(num) + 1);
        }
        int[] topKArr = new int[k];
        for (Entry e : map.entrySet()) {

        }

        return topKArr;
    }
}
