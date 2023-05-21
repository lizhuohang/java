package com.lzh.interview.jdk;

import java.util.Stack;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2020-08-17
 */
public class TreeMapTest {
    public static void main(String[] args) {

        //        TreeMap<Integer, String> treeMap = new TreeMap<>();
        //
        //        treeMap.put(0, "<=3");
        //        treeMap.put(4, "4-7");
        //        treeMap.put(8, "8-30");
        //        treeMap.put(31, "31-90");
        //        treeMap.put(91, "91-180");
        //        treeMap.put(181, "181-365");
        //        treeMap.put(366, ">365");
        //
        //        System.out.println("0:  " + treeMap.floorEntry(0).getValue());
        //        System.out.println("1:  " + treeMap.floorEntry(1).getValue());
        //        System.out.println("2:  " + treeMap.floorEntry(2).getValue());
        //        System.out.println("3:  " + treeMap.floorEntry(3).getValue());
        //        System.out.println("4:  " + treeMap.floorEntry(4).getValue());
        //        System.out.println("5:  " + treeMap.floorEntry(5).getValue());
        //        System.out.println("7:  " + treeMap.floorEntry(7).getValue());
        //        System.out.println("8:  " + treeMap.floorEntry(8).getValue());
        //        System.out.println("9:  " + treeMap.floorEntry(9).getValue());
        //        System.out.println("29: " + treeMap.floorEntry(29).getValue());
        //        System.out.println("30: " + treeMap.floorEntry(30).getValue());
        //        System.out.println("31: " + treeMap.floorEntry(31).getValue());
        //        System.out.println("32: " + treeMap.floorEntry(32).getValue());
        //        System.out.println("89: " + treeMap.floorEntry(89).getValue());
        //        System.out.println("90: " + treeMap.floorEntry(90).getValue());
        //        System.out.println("91: " + treeMap.floorEntry(91).getValue());
        //        System.out.println("120:    " + treeMap.floorEntry(120).getValue());
        //        System.out.println("364:    " + treeMap.floorEntry(364).getValue());
        //        System.out.println("365:    " + treeMap.floorEntry(365).getValue());
        //        System.out.println("366:    " + treeMap.floorEntry(366).getValue());

        //        Set<Long> deletingSet = new CopyOnWriteArraySet();
        //        System.out.println(deletingSet.add(1L));
        //        System.out.println(deletingSet.add(1L));
        //        System.out.println(deletingSet.remove(1L));
        //        System.out.println(deletingSet.add(1L));
        //        System.out.println(deletingSet.add(1L));

        //        System.out.println(reverse(100));
        //        System.out.println(reverse(-100));
        //        System.out.println(reverse(123));
        //        System.out.println(reverse(-123));
        //        System.out.println(reverse(120));
        //        System.out.println(reverse(-120));
        //        System.out.println(reverse(102));
        //        System.out.println(reverse(-102));

    }

    public String reverseStringII(String str, int size) {
        char[] charArr = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = size; i >= 0; i++) {
            stack.push(charArr[i]);
        }

        for (int j = charArr.length - 1; j > size; j--) {
            stack.push(charArr[j]);
        }

        StringBuilder sb = new StringBuilder();
        return null;
    }

    public static int reverse(int num) {
        int f = num < 0 ? -1 : 1;
        int tmp = f * num;
        int result = 0;
        while (tmp / 10 > 0) {
            if (result != 0 || tmp % 10 != 0) {
                result = result * 10 + tmp % 10;
            }
            tmp = tmp / 10;
        }
        result = result * 10 + tmp;
        return result * f;
    }
}
