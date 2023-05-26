package com.lzh.interview.algorithm;

/**
 * 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 * https://leetcode.cn/problems/swap-nodes-in-pairs/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-26
 */
public class SwapPairs {

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[] {1, 2, 3, 4, 5, 6, 7, 8});
        ListNode head1 = new ListNode(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        swap(head).print();
        swap(head1).print();
    }

    private static ListNode swap(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.getNext();
        ListNode third = head.getNext().getNext();
        second.setNext(first);
        first.setNext(swap(third));
        return second;
    }
}
