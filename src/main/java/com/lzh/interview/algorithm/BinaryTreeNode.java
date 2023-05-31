package com.lzh.interview.algorithm;

/**
 * 二叉树节点
 *
 * @author lizhuohang <li_zhuohang@126.com>
 * Created on 2023-05-31
 */
public class BinaryTreeNode implements Comparable<BinaryTreeNode> {

    public BinaryTreeNode(BinaryTreeNode left, BinaryTreeNode right) {
        this.left = left;
        this.right = right;
    }

    private int value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int compareTo(BinaryTreeNode o) {
        return this.value - o.getValue();
    }
}
