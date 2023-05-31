package com.lzh.interview.algorithm;

import java.util.Stack;

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * https://leetcode.cn/problems/basic-calculator/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-30
 */
public class BasicCalculator {

    public static void main(String[] args) {
        System.out.println(calculate("1 + 1"));
        System.out.println(calculate(" 2-1 + 2 "));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    private static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        int sign = 1;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '+' || c == '-') {
                res = res + num * sign;
                num = 0;
                sign = c == '+' ? 1 : -1;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res = num * stack.pop() + stack.pop();
                num = 0;
            }
        }
        res = res + num * sign;
        return res;
    }
}
