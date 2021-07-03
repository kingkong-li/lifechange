package com.jingang.lifechange.algorithm.struct.tree;

import com.jingang.lifechange.algorithm.struct.tree.bean.TreeNode;

/**
 * @Description: 给定一棵二叉树和求和，确定树上是否具有根到叶路径，使得沿路径的所有值相加等于给定的总和
 * @Author: jingang.Li
 * @CreateTime:2021/7/1-11:28 AM
 * @changeTime:
 */
public class TreeSum {
    public boolean findSum(int sum, TreeNode treeNode){
        if(treeNode==null){
            return false;
        }
        if(treeNode.left==null && treeNode.right==null){
            return sum==treeNode.value;
        }
        sum=sum-treeNode.value;
        return findSum(sum,treeNode.left) || findSum(sum,treeNode.right);
    }
}
