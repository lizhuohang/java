package com.lzh.interview.algorithm.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: lizhuohang
 * @Date: Created in 18:02 18/3/27
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {43, 21, 1, 56, 22, 31, 50};
        System.out.println(JSONObject.toJSONString(sort(a)));
    }

//    public static int[] sort(int[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = arr.length - 1; j > i; j--) {
//                if (arr[j] < arr[j - 1]) {
//                    int tmp = arr[j];
//                    arr[j] = arr[j - 1];
//                    arr[j - 1] = tmp;
//                }
//            }
//        }
//        return arr;
//    }


    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
        return arr;
    }
}
