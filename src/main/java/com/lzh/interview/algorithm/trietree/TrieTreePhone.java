package com.lzh.interview.algorithm.trietree;

/**
 * 纯链表形式实现
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-25
 */
public class TrieTreePhone {

    public static void main(String[] args) {

        Node head = init(new String[] {
                "18612188003",
                "18612188002",
                "18112188002",
                "15101657671"
        });

        System.out.println(match("18612188002", head));
        System.out.println(match("18612188001", head));
        System.out.println(match("15101657671", head));
        System.out.println(match("1", head));

    }

    private static boolean match(String phone, Node head) {
        Node nowNode = head;
        for (int i = 0; i < phone.length(); i++) {
            if (nowNode == null) {
                return false;
            }
            int value = Integer.valueOf(phone.charAt(i) + "");

            while (nowNode != null) {
                if (nowNode.getValue() == value) {
                    if (nowNode.isEnd) {
                        return true;
                    }
                    nowNode = nowNode.getNextLevel();
                    break;
                } else {
                    nowNode = nowNode.getSameLevelNext();
                }
            }
            if (nowNode == null) {
                return false;
            }
        }
        return false;
    }

    private static Node init(String[] phoneArr) {
        Node head = new Node(1);

        for (String phone : phoneArr) {
            Node nowNode = head;
            for (int i = 1; i < phone.length(); i++) {
                int value = Integer.valueOf(phone.charAt(i) + "");
                Node nextHead = nowNode.getNextLevel();
                if (nextHead == null) {
                    nowNode.setNextLevel(new Node(value));
                    nowNode = nowNode.getNextLevel();
                    if (i == phone.length() - 1) {
                        nowNode.setEnd(true);
                    }
                    continue;
                }
                Node preNode = null;
                while (nextHead != null) {
                    if (nextHead.getValue() == value) {
                        break;
                    }
                    preNode = nextHead;
                    nextHead = nextHead.getSameLevelNext();

                }
                if (nextHead == null) {
                    preNode.setSameLevelNext(new Node(value));
                    nextHead = preNode.getSameLevelNext();
                    if (i == phone.length() - 1) {
                        nextHead.setEnd(true);
                    }
                }
                nowNode = nextHead;
            }
        }

        return head;
    }

    private static class Node {
        private int value;
        private Node nextLevel;
        private Node sameLevelNext;

        private boolean isEnd = false;

        public void print() {
            System.out.print(this.value);
            Node n = this.sameLevelNext;
            while (n != null) {
                System.out.print(n.value + "  ");
                n = n.sameLevelNext;
            }
        }

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNextLevel() {
            return nextLevel;
        }

        public void setNextLevel(Node nextLevel) {
            this.nextLevel = nextLevel;
        }

        public Node getSameLevelNext() {
            return sameLevelNext;
        }

        public void setSameLevelNext(Node sameLevelNext) {
            this.sameLevelNext = sameLevelNext;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }
    }
}
