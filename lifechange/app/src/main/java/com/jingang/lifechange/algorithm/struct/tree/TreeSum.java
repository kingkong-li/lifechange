package com.jingang.lifechange.algorithm.struct.tree;

import com.jingang.lifechange.algorithm.struct.tree.bean.TreeNode;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/7/1-11:28 AM
 * @changeTime:
 */
public class TreeSum {
    public boolean findSum(int sum, TreeNode treeNode){
        if(treeNode==null){
            if(sum==0){
                return true;
            }else {
                return false;
            }

        }
        sum=sum-treeNode.value;
        return findSum(sum,treeNode.left) || findSum(sum,treeNode.right);
    }
}
