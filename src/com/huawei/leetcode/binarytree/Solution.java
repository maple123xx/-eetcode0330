package com.huawei.leetcode.binarytree;

public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {val = x;}
    }

    //538. 把二叉搜索树转换为累加树
    private int tmp = 0;
    public TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }
    private void convert(TreeNode root) {
        if (root == null) {
            return;
        }
        convert(root.right);
        root.val += tmp;
        tmp = root.val;
        convert(root.left);
    }

    //543 二叉树的直径
    private int dis = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return dis;
    }
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        dis = Math.max(left + right, dis);
        return 1 + Math.max(left, right); //返回树高
    }

    //合并二叉树
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode t = new TreeNode(t1.val + t2.val);
        t.left = mergeTrees(t1.left, t2.left);
        t.right = mergeTrees(t1.right, t2.right);
        return t;
    }
}
