package com.lzh.interview.algorithm;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

/**
 * 文本左右对齐
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * 注意:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * https://leetcode.cn/problems/text-justification/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-30
 */
public class TextJustification {

    public static void main(String[] args) {
        //        String[] words = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
        //        System.out.println(JSONObject.toJSONString(fullJustify(words, 16)));

        //        String[] words = new String[] {"What", "must", "be", "acknowledgment", "shall", "be"};
        //        System.out.println(JSONObject.toJSONString(fullJustify(words, 16)));

        String[] words =
                new String[] {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a",
                        "computer.", "Art", "is", "everything", "else", "we", "do"};
        System.out.println(JSONObject.toJSONString(fullJustify(words, 20)));

    }

    private static ArrayList<String> fullJustify(String[] words, int maxWidth) {
        int[] wordLength = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            wordLength[i] = words[i].length();
        }

        ArrayList<Integer> splitIndexArr = new ArrayList<>();
        int length = 0;
        for (int i = 0; i < words.length; i++) {
            length += words[i].length();
            if (length < maxWidth) {
                if (i == words.length - 1) {
                    splitIndexArr.add(i);
                }
                length += 1;
                continue;
            } else if (length == maxWidth) {
                splitIndexArr.add(i);
                length = 0;
            } else {
                splitIndexArr.add(i - 1);
                i = i - 1;
                length = 0;
            }
        }

        ArrayList<String> result = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < splitIndexArr.size(); i++) {
            int end = splitIndexArr.get(i);
            int lengthT = 0;
            int wordNum = 0;
            int jTmp = j;
            for (; j <= end; j++, wordNum++) {
                lengthT += words[j].length();
            }
            int gap = maxWidth - lengthT;
            int notAvg = 0;
            int avg = gap;
            if (wordNum != 1) {
                notAvg = gap % (wordNum - 1);
                avg = gap / (wordNum - 1);
            }

            StringBuilder sb = new StringBuilder();
            for (j = jTmp; j <= end; j++) {
                sb.append(words[j]);
                int spaceNum = avg;
                if (j == end) {
                    continue;
                }
                if (j == jTmp) {
                    spaceNum += notAvg;
                }
                for (int p = 0; p < spaceNum; p++) {
                    sb.append(" ");
                }
            }
            result.add(sb.toString());
        }
        return result;
    }
}
