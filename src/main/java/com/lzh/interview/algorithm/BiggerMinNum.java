package com.lzh.interview.algorithm;

import java.util.ArrayList;

/**
 * 给定一个从小到大排好序的集合，集合中元素都在0-9之间，但未必全包含
 * 给定一个制定正整数K
 * 请用A中的元素组成一个大于K的最小正整数
 * <p>
 * 例如 A=[1,0] k=21 输出结果应该是100
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-21
 */
public class BiggerMinNum {

    private static int[] arr = new int[] {3, 6, 8, 9};

    public static void main(String[] args) {
        System.out.println(biggerMinNum(arr, 123));
        System.out.println(biggerMinNum(arr, 12));
        System.out.println(biggerMinNum(arr, 1));
        System.out.println(biggerMinNum(arr, 19));
        System.out.println(biggerMinNum(arr, 10));
        System.out.println(biggerMinNum(arr, 99));
        System.out.println(biggerMinNum(arr, 93));
        System.out.println(biggerMinNum(arr, 73));
        System.out.println(biggerMinNum(arr, 63));
        System.out.println(biggerMinNum(arr, 67));
    }

    private static int biggerMinNum(int[] arr, int num) {
        // 这里假设arr是已经按照升序排好序的数组
        // 将num处理成一个ArrayList，从最大位开始处理
        // 在arr中寻找当前位相同或者大于它的第一个数
        // 如果相同，往下一位递归
        // 如果大于，直接使用，后面补arr中的第一个值
        // 如果没有，再回退到上一位，并寻找比他大的值

        ArrayList<Integer> numList = new ArrayList<>();
        while (num > 0) {
            numList.add(0, num % 10);
            num = num / 10;
        }
        return loop(arr, numList, 0, false);
    }

    private static int loop(int[] arr, ArrayList<Integer> numList, int numPosition, boolean mustBigger) {
        if (!mustBigger && numPosition == numList.size() - 1) {
            return loop(arr, numList, numPosition, true);
        }
        int nowNum = numList.get(numPosition);
        for (int i = 0; i < arr.length; i++) {
            if (!mustBigger && arr[i] == nowNum) {
                return loop(arr, numList, numPosition + 1, false);
            } else if (arr[i] > nowNum) {
                return getResultUseBigger(arr, numList, numPosition, i);
            }
        }
        // 没有比他大的
        if (numPosition == 0) {
            return getResultMoreOne(arr, numList, numPosition);
        }
        return loop(arr, numList, numPosition - 1, true);
    }

    private static int getResultUseBigger(int[] arr, ArrayList<Integer> numList, int numPosition, int biggerPosition) {
        int result = 0;
        for (int i = 0; i < numList.size(); i++) {
            if (i < numPosition) {
                result = result * 10 + numList.get(i);
            } else if (i == numPosition) {
                result = result * 10 + arr[biggerPosition];
            } else {
                result = result * 10 + arr[0];
            }
        }
        return result;
    }

    private static int getResultMoreOne(int[] arr, ArrayList<Integer> numList, int numPosition) {
        int result = 0;
        if (arr[0] > 0) {
            result = arr[0];
        } else {
            result = arr[1];
        }
        for (int i = 0; i < numList.size(); i++) {
            result = result * 10 + arr[0];
        }
        return result;
    }
}
