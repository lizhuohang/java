package com.lzh.interview.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * https://leetcode.cn/problems/word-search/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-06-05
 */
public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};

        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";

        System.out.println(exist(board, word1));
        System.out.println(exist(board, word2));
        System.out.println(exist(board, word3));
        System.out.println(exist(new char[][] {{'a', 'b'}}, "ba"));
    }

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            char[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                if (word.charAt(0) == row[j]) {
                    Set<String> set = new HashSet<>();
                    set.add(i + "_" + j);
                    if (exist(board, word.substring(1), i, j, set)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean exist(char[][] board, String word, int i, int j, Set<String> set) {
        if (word.equals("")) {
            return true;
        }
        if (i - 1 >= 0 && word.charAt(0) == board[i - 1][j] && !set.contains(i - 1 + "_" + j)) {
            set.add(i - 1 + "_" + j);
            if (exist(board, word.substring(1), i - 1, j, set)) {
                return true;
            }
            set.remove(i - 1 + "_" + j);
        }

        if (i + 1 < board.length && word.charAt(0) == board[i + 1][j] && !set.contains(i + 1 + "_" + j)) {
            set.add(i + 1 + "_" + j);
            if (exist(board, word.substring(1), i + 1, j, set)) {
                return true;
            }
            set.remove(i + 1 + "_" + j);
        }

        if (j - 1 >= 0 && word.charAt(0) == board[i][j - 1] && !set.contains(i + "_" + (j - 1))) {
            set.add(i + "_" + (j - 1));
            if (exist(board, word.substring(1), i, j - 1, set)) {
                return true;
            }
            set.remove(i + "_" + (j - 1));
        }

        if (j + 1 < board[i].length && word.charAt(0) == board[i][j + 1] && !set.contains(i + "_" + (j + 1))) {
            set.add(i + "_" + (j + 1));
            if (exist(board, word.substring(1), i, j + 1, set)) {
                return true;
            }
            set.remove(i + "_" + (j + 1));
        }
        return false;
    }
}
