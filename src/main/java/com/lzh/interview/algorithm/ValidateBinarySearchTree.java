package com.lzh.interview.algorithm;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * **节点的左子树只包含 小于 当前节点的数。
 * **节点的右子树只包含 大于 当前节点的数。
 * **所有左子树和右子树自身必须也是二叉搜索树。
 * https://leetcode.cn/problems/validate-binary-search-tree/
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-31
 */
public class ValidateBinarySearchTree {

    private BinaryTreeNode pre;

    private boolean validate(BinaryTreeNode head) {
        if (head == null) {
            return true;
        }

        if (!validate(head.getLeft())) {
            return false;
        }

        if (pre != null && head.getValue() <= pre.getValue()) {
            return false;
        }

        pre = head;

        if (!validate(head.getRight())) {
            return false;
        }

        return true;
    }
}
