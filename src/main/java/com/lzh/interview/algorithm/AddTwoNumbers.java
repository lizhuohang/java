package com.lzh.interview.algorithm;

import com.alibaba.fastjson.JSONObject;

/**
 * https://leetcode.cn/problems/add-two-numbers/
 *
 * @author lizhuohang <lizhuohang@kuaishou.com>
 * Created on 2023-05-24
 */
public class AddTwoNumbers {

    private static class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        public ListNode(int[] valueArr) {
            this.value = valueArr[0];
            ListNode tmp = this;
            for (int i = 1; i < valueArr.length; i++) {
                tmp.next = new ListNode(valueArr[i]);
                tmp = tmp.next;
            }
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(new int[] {9, 9, 9, 9, 9, 9, 9});
        ListNode b = new ListNode(new int[] {9, 9, 9, 9});
        print(add(a, b));
    }

    private static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.getValue() + " ");
            listNode = listNode.getNext();
        }
    }

    private static ListNode add(ListNode a, ListNode b) {
        ListNode result = new ListNode(0);
        ListNode tmp = result;
        ListNode at = a;
        ListNode bt = b;
        int i = 0;
        while (at != null || bt != null) {
            int aValue = 0;
            int bValue = 0;
            if (at != null) {
                aValue = at.getValue();
                at = at.getNext();
            }
            if (bt != null) {
                bValue = bt.getValue();
                bt = bt.getNext();
            }
            if (i != 0) {
                if (tmp.getNext() == null) {
                    tmp.setNext(new ListNode(0));
                }
                tmp = tmp.getNext();
            }
            i++;
            int value = aValue + bValue + tmp.getValue();
            boolean supperAdd = false;
            if (value >= 10) {
                supperAdd = true;
                value = value % 10;
            } else {
                supperAdd = false;
            }
            tmp.setValue(value);
            if (supperAdd) {
                tmp.setNext(new ListNode(1));
            }
        }
        return result;
    }

}
