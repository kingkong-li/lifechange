package com.jingang.lifechange.algorithm.struct.tree;

import com.jingang.lifechange.algorithm.struct.tree.bean.TreeNode;

/**
 * @Description: 完全二叉树
 * @Author: jingang.Li
 * @CreateTime: 2021/7/5-3:03 PM
 */
public class CompleteBinaryTree {

    /**
     * 计算完全二叉树节点个数
     * @param treeNode
     * @return 节点个数
     */
    public int calculateNodes(TreeNode treeNode){
        if(treeNode==null){
            return 0;
        }
        TreeNode leftTree=treeNode;
        TreeNode rightTree=treeNode;
        int leftDeep=1;
        while (leftTree.left!=null){
            leftDeep++;
            leftTree=leftTree.left;
        }
        int rightDeep=1;
        while (rightTree.right!=null){
            rightDeep++;
            rightTree=rightTree.right;
        }
        // 如果左右子树深度相同 那么它是一个满二叉树 满二叉树 节点是有数组公式的
        if(leftDeep==rightDeep){
            return (int)Math.pow(2,leftDeep)-1;
        }
//         按照一般二叉树进行计算
        return calculateNodes(treeNode.left)+calculateNodes(treeNode.right)+1;

    }

    public int calculateNodes2(TreeNode treeNode){
        if(treeNode==null){
            return 0;
        }
        TreeNode leftTree=treeNode;
        int leftDeep=1;
        while (leftTree.left!=null){
            leftDeep++;
            leftTree=leftTree.left;
        }
        int rightLeftDeep=1;
        TreeNode temNode=treeNode.right;
        while (temNode!=null){
            rightLeftDeep++;
            temNode=temNode.left;
        }
        if(leftDeep>rightLeftDeep){
            return 2*calculateNodes2(treeNode.left);
        }else{
            return 2*calculateNodes2(treeNode.right)+1;
        }

    }

}
