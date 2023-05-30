package com.lzh.interview.algorithm;

import java.util.ArrayList;

/**
 * 还没完全写完
 * N字形变换
 * https://leetcode.cn/problems/zigzag-conversion/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-30
 */
public class NWordConvert {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }

    /**
     * 路由规则，0->0,0 1->1,0 2->2,0 3->1,1 4->0,1 5->1,2
     * 进行简化，0->0 1->1 2->2 3->1 4->0 5->1 6->2 7->1 8->0 9->1 10->2 11->1
     */
    private static String convert(String word, int numRows) {
        ArrayList<ArrayList<Character>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            result.add(new ArrayList<>());
        }
        int index = 0;
        int tmpNum = numRows + numRows - 1;
        for (int i = 0; i < word.length(); i++) {
            int t = i % tmpNum;
            if (i != 0) {
                if (t > numRows - 1) {
                    index--;
                } else {
                    index++;
                }
            }
            System.out.println(t + " - " + index);
            //            result.get(index).add(word.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Character> c = result.get(i);
            for (Character character : c) {
                sb.append(character);
            }
        }
        return sb.toString();
    }
}
