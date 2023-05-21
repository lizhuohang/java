package com.lzh.interview.algorithm;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2020-10-20
 */
public class Link {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static class Solution {

        public static void main(String[] args) {
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(5);
            head.next.next.next.next.next = new ListNode(6);
            head.next.next.next.next.next.next = new ListNode(7);
            reorderList(head);
            ListNode tmp = head;
            System.out.print(tmp.val);
            while (tmp.next != null) {
                tmp = tmp.next;
                System.out.print(" -> ");
                System.out.print(tmp);
            }
        }

        public static void reorderList(ListNode head) {
            ListNode tmp = head;
            while (tmp.next != null) {
                handleLast(head);
                tmp = tmp.next.next;
            }

        }

        private static void handleLast(ListNode head) {
            ListNode last = getLast(head);
            if (head.next == null || last == null) {
                return;
            }

            ListNode tmp = head.next;
            head.next = last;
            last.next = tmp;
            return;
        }

        private static ListNode getLast(ListNode head) {
            ListNode last = head;
            while (last.next != null) {
                last = last.next;
            }
            return last;
        }
    }
}
