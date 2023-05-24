package com.lzh.interview.algorithm;

/**
 * 删除链表的倒数第N个节点
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-24
 */
public class RemoveNthFormEnd {

    public static void main(String[] args) {
        remove(new ListNode(new int[] {1, 2, 3, 4, 5, 6}), 2).print();
    }

    private static ListNode remove(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.getNext();
        }
        int nthIndexFromHead = len - n;
        if (nthIndexFromHead < 0 || nthIndexFromHead >= len) {
            return null;
        }
        node = head;
        for (int i = 0; i < nthIndexFromHead - 1; i++) {
            node = node.getNext();
        }
        node.setNext(node.getNext().getNext());
        return head;
    }
}
