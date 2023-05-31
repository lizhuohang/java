package com.lzh.interview.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-31
 */
public class BinaryTreeLevelOrderTraversal {


    public List<List<Integer>> levelOrder(BinaryTreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int length = queue.size();
            List<Integer> nowResult = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                BinaryTreeNode node = queue.poll();
                nowResult.add(node.getValue());
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
            result.add(nowResult);
        }
        return result;
    }
}
