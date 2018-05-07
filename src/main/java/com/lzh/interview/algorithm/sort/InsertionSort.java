package com.lzh.interview.algorithm.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: lizhuohang
 * @Date: Created in 18:52 18/3/27
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] a = {43, 21, 1, 56, 22, 31, 50};
        System.out.println(JSONObject.toJSONString(sort(a)));
    }

    public static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int pre = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > pre) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = pre;
        }
        return arr;
    }

}
