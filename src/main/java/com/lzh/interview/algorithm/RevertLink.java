package com.lzh.interview.algorithm;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2020-10-19
 */
public class RevertLink {
    public static void main(String[] args) {
        Node node = createList("abcdeefg");
        System.out.println(JSONObject.toJSONString(node));
        System.out.println(JSONObject.toJSONString(revert(node)));
    }

    private static Node revert(Node start) {
        if (start == null || start.getNext() == null) {
            return start;
        }
        Node second = start.getNext();
        Node third = second.getNext();
        start.setNext(null);
        while (true) {
            second.setNext(start);
            start = second;
            second = third;
            if (second == null) {
                break;
            }
            third = third.getNext();
        }
        return start;
    }

    private static Node createList(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Node start = new Node();
        Node node = start;
        for (int i = 0; i < s.length(); i++) {
            node.setCharacter(s.charAt(i));
            if (i < s.length() - 1) {
                node.setNext(new Node());
            }
            node = node.getNext();
        }
        return start;
    }

    private static class Node {
        private Character character;
        private Node next;

        public Node() {
        }

        public Node(Character character, Node next) {
            this.character = character;
            this.next = next;
        }

        public Character getCharacter() {
            return character;
        }

        public void setCharacter(Character character) {
            this.character = character;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
