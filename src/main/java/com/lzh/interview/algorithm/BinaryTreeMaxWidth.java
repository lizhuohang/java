package com.lzh.interview.algorithm;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 二叉树最大宽度
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-30
 */
public class BinaryTreeMaxWidth {

    public static void main(String[] args) {
        Node head =
                new Node(
                        new Node(
                                new Node(
                                        new Node(
                                                new Node(
                                                        new Node(null, null),
                                                        new Node(null, new Node(null, new Node(null, null)))
                                                ),
                                                new Node(
                                                        new Node(null, null),
                                                        new Node(null, null)
                                                )
                                        ),
                                        new Node(null, null)
                                ),
                                new Node(
                                        new Node(
                                                new Node(
                                                        new Node(null, null),
                                                        new Node(null, null)
                                                ),
                                                null),
                                        null)
                        ),
                        new Node(
                                new Node(null, null),
                                null
                        )
                );

        System.out.println(maxWidth(head));
    }

    private static int maxWidth(Node head) {
        int maxWidth = 0;
        Queue<Node> queue = new PriorityQueue<Node>();
        queue.add(head);
        while (!queue.isEmpty()) {
            int length = queue.size();
            maxWidth = length > maxWidth ? length : maxWidth;
            for (int i = 0; i < length; i++) {
                Node node = queue.poll();
                Node left = node.getLeft();
                Node right = node.getRight();
                if (left != null) {
                    queue.add(left);
                }
                if (right != null) {
                    queue.add(right);
                }
            }
        }
        return maxWidth;
    }

    private static class Node implements Comparable<Node> {

        public Node(Node left, Node right) {
            this.left = left;
            this.right = right;
        }

        private int value;
        private Node left;
        private Node right;

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int compareTo(Node o) {
            return this.value - o.getValue();
        }
    }

}
