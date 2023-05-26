package com.lzh.interview.algorithm;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-24
 */
public class ListNode {
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

    public void print() {
        ListNode listNode = this;
        while (listNode != null) {
            System.out.print(listNode.getValue() + " ");
            listNode = listNode.getNext();
        }
        System.out.println();
    }
}