package com.lzh.interview.algorithm.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: lizhuohang
 * @Date: Created in 18:43 18/3/27
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] a = {43, 21, 1, 56, 22, 31, 50};
        System.out.println(JSONObject.toJSONString(sort(a)));
    }

//    public static int[] sort(int[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            int minIndex = i;
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[j] < arr[minIndex]) {
//                    minIndex = j;
//                }
//            }
//            int tmp = arr[minIndex];
//            arr[minIndex] = arr[i];
//            arr[i] = tmp;
//        }
//        return arr;
//    }

    public static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }
}
