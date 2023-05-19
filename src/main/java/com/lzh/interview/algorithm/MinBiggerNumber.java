package com.lzh.interview.algorithm;

import com.lzh.interview.algorithm.sort.QuickSort;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2020-06-24
 */
public class MinBiggerNumber {
    public static void main(String[] args) {
        printArray(findMinBiggerNum(new int[] {1, 3, 2, 4}, 1));
        printArray(findMinBiggerNum(new int[] {1, 3, 4, 2}, 1));
        printArray(findMinBiggerNum(new int[] {3, 3, 4, 2}, 1));

    }

    /**
     *
     * @param numArray          需要操作的数，转化来的数组
     * @param position          当前更换哪一位，1 十位，2 百位，3 千位 。。。
     * @return
     */
    public static int[] findMinBiggerNum(int[] numArray, int position) {
        int targetPosition = numArray.length - position - 1;
        // 找到array中序号从 targetPosition+1 - numArray.length-1 中比 targetPosition 大的最小值就可以了
        int minBiggerPosition = -1;
        int minBigger = 10;
        for (int i = numArray.length - 1; i > targetPosition; i--) {
            if (numArray[i] > numArray[targetPosition]) {
                if (minBigger > numArray[i]) {
                    minBiggerPosition = i;
                    minBigger = numArray[i];
                }
            }
        }
        if (minBiggerPosition != -1) {
            int tmp = numArray[minBiggerPosition];
            numArray[minBiggerPosition] = numArray[targetPosition];
            numArray[targetPosition] = tmp;
            // 然后再把targetPosition之后的做个升序排列
            QuickSort.quitSort(numArray, targetPosition + 1, numArray.length - 1);
            return numArray;
        }
        return findMinBiggerNum(numArray, position + 1);
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i);
        }
        System.out.println();
    }
}
