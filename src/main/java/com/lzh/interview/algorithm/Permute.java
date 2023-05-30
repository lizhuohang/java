package com.lzh.interview.algorithm;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * https://leetcode.cn/problems/permutations/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-30
 */
public class Permute {

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(permute(new int[] {1, 2, 3})));
    }

    private static ArrayList<ArrayList<Integer>> permute(int[] arr) {
        return permute(arr, 0);
    }

    private static ArrayList<ArrayList<Integer>> permute(int[] arr, int index) {
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        if (index == arr.length - 1) {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(arr[index]);
            result.add(a);
            return result;
        }
        result = permute(arr, index + 1);
        ArrayList<ArrayList<Integer>> finalResult = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            ArrayList<Integer> tmp = result.get(i);
            for (int j = 0; j <= tmp.size(); j++) {
                ArrayList<Integer> t = new ArrayList<>(tmp);
                t.add(j, arr[index]);
                finalResult.add(t);
            }
        }
        return finalResult;
    }
}
