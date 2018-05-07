package com.lzh.interview.algorithm.sort;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: lizhuohang
 * @Date: Created in 14:34 18/3/29
 */
public class QS {

    public static void main(String[] args) {
        int[] a = {43, 21, 1, 56, 22, 31, 50};
        System.out.println(JSONObject.toJSONString(sort(a)));
    }

    private static int[] sort(int[] arr) {
        return sort(arr, 0, arr.length - 1);
    }

    private static int[] sort(int[] arr, int begin, int end) {
        if (arr == null || begin >= end) {
            return arr;
        }
        int center = getCenter(arr, begin, end);
        sort(arr, begin, center - 1);
        sort(arr, center + 1, end);
        return arr;
    }

    private static int getCenter(int[] arr, int begin, int end) {
        int v = arr[begin];
        int i = begin, j = end;
        while (i < j) {
            while (arr[i] <= v && i < end) {
                i++;
            }
            while (arr[j] > v && j >= begin) {
                j--;
            }
            if (i < j) {
                sweep(arr, i, j);
            }
        }
        if (j != begin) {
            sweep(arr, begin, j);
        }
        return j;
    }

    private static int[] sweep(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return arr;
    }

}
