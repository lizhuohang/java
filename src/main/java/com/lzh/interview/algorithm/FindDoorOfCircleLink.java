package com.lzh.interview.algorithm;

import com.alibaba.fastjson.JSONObject;

/**
 * 单向链表找环入口
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-24
 */
public class FindDoorOfCircleLink {

    private static class ListNode {
        private ListNode next;
        private int value;

        public ListNode(int value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public ListNode setNext(ListNode next) {
            this.next = next;
            return this;
        }

        public int getValue() {
            return value;
        }

        public ListNode setValue(int value) {
            this.value = value;
            return this;
        }
    }

    private final static ListNode START = new ListNode(0);
    private final static ListNode doorOfCircle = new ListNode(4);

    static {

        START.setNext(
                new ListNode(1).setNext(
                        new ListNode(2).setNext(
                                new ListNode(3).setNext(
                                        doorOfCircle.setNext(
                                                new ListNode(5).setNext(
                                                        new ListNode(6).setNext(
                                                                new ListNode(7).setNext(
                                                                        new ListNode(8).setNext(
                                                                                new ListNode(9).setNext(
                                                                                        doorOfCircle))))))))));
    }

    public static void main(String[] args) {
        ListNode slow = START, fast = START;
        // 先看是否为环
        do {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        } while (slow != fast && slow != null && fast != null && fast.getNext() != null);
        boolean isCircle = fast != null && fast.getNext() != null;
        if (!isCircle) {
            System.out.println("list is not a circle");
            return;
        }
        fast = START;
        while (fast != slow) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        System.out.println("the real door of the circle is : " + JSONObject.toJSONString(doorOfCircle.getValue()));
        System.out.println("find the door of the circle is : " + JSONObject.toJSONString(slow.getValue()));
        System.out.println("find the door of the circle is : " + JSONObject.toJSONString(fast.getValue()));
    }
}
