package com.lzh.interview.algorithm;

/**
 * https://leetcode.cn/problems/rotate-list/
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-24
 */
public class RotateRight {

    public static void main(String[] args) {
        rotate(new ListNode(new int[] {1, 2, 3, 4, 5}), 2).print();
    }

    // 解题思路，循环拿到链表长度，然后用k模链表长度，然后截断拼到前面去
    private static ListNode rotate(ListNode head, int k) {
        int len = 0;
        ListNode tmp = head;
        while (tmp != null) {
            len++;
            tmp = tmp.getNext();
        }
        int kValue = k % len;
        tmp = head;
        ListNode newEnd = null;
        ListNode newHead = null;
        for (int i = 0; i < len - 1; i++) {
            if (i == len - kValue - 1) {
                newEnd = tmp;
                newHead = tmp.getNext();
            }
            tmp = tmp.getNext();
        }
        tmp.setNext(head);
        newEnd.setNext(null);
        return newHead;
    }
}
