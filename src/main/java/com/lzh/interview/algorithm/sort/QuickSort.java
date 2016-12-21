package com.lzh.interview.algorithm.sort;

/**
 * Created by lizhuohang on 2016/12/21.
 */
public class QuickSort {

    public static void main(String[] args) {
        int [] a = quitSort(new int[]{4,100,10,28,3,-18});
        for (int i : a){
            System.out.println(i);
        }
    }

    private static void print(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static int[] quitSort(int[] array) {
        if (array != null) {
            return quitSort(array, 0, array.length - 1);
        }
        return null;
    }

    private static int[] quitSort(int[] array, int beg, int end) {
        if (beg >= end || array == null)
            return array;
        int p = partion(array, beg, end);
        quitSort(array, beg, p - 1);
        quitSort(array, p + 1, end);
        return array;
    }

    private static int partion(int[] array, int beg, int end) {
        int first = array[beg];
        int i = beg, j = end;
        while (i < j) {
            while (array[i] <= first && i < end) {
                i++;
            }
            while (array[j] > first && j >= beg) {
                j--;
            }
            if (i < j) {
                array[i] = array[i] ^ array[j];
                array[j] = array[i] ^ array[j];
                array[i] = array[i] ^ array[j];
            }
            print(array);
        }
        System.out.println("i : " + i + " ---- j : " + j);
        if (j != beg) {
            array[j] = array[beg] ^ array[j];
            array[beg] = array[beg] ^ array[j];
            array[j] = array[beg] ^ array[j];
        }
        print(array);
        return j;
    }
}
