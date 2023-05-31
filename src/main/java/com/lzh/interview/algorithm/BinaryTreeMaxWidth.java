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
        BinaryTreeNode head =
                new BinaryTreeNode(
                        new BinaryTreeNode(
                                new BinaryTreeNode(
                                        new BinaryTreeNode(
                                                new BinaryTreeNode(
                                                        new BinaryTreeNode(
                                                                null,
                                                                null
                                                        ),
                                                        new BinaryTreeNode(
                                                                null,
                                                                new BinaryTreeNode(
                                                                        null,
                                                                        new BinaryTreeNode(null, null)
                                                                )
                                                        )
                                                ),
                                                new BinaryTreeNode(
                                                        new BinaryTreeNode(null, null),
                                                        new BinaryTreeNode(null, null)
                                                )
                                        ),
                                        new BinaryTreeNode(null, null)
                                ),
                                new BinaryTreeNode(
                                        new BinaryTreeNode(
                                                new BinaryTreeNode(
                                                        new BinaryTreeNode(null, null),
                                                        new BinaryTreeNode(null, null)
                                                ),
                                                null),
                                        null)
                        ),
                        new BinaryTreeNode(
                                new BinaryTreeNode(null, null),
                                null
                        )
                );

        System.out.println(maxWidth(head));
    }

    private static int maxWidth(BinaryTreeNode head) {
        int maxWidth = 0;
        Queue<BinaryTreeNode> queue = new PriorityQueue<BinaryTreeNode>();
        queue.add(head);
        while (!queue.isEmpty()) {
            int length = queue.size();
            maxWidth = length > maxWidth ? length : maxWidth;
            for (int i = 0; i < length; i++) {
                BinaryTreeNode node = queue.poll();
                BinaryTreeNode left = node.getLeft();
                BinaryTreeNode right = node.getRight();
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
}
