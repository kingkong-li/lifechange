package com.jingang.lifechange.algorithm.struct.tree;

import com.jingang.lifechange.algorithm.struct.tree.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/7/1-11:12 AM
 * @changeTime:
 */
class BaseTreeOperation {

    /**
     * 通过广度优先遍历产生一颗完全二叉树
     * @param intArray
     * @return
     */
    public static TreeNode generateTree(int[] intArray){
        TreeNode treeRootNode=new TreeNode(intArray[0]);
        Queue<TreeNode> treeNodeQueue=new ArrayDeque<>();
        treeNodeQueue.offer(treeRootNode);
        int i=1;
        while (!treeNodeQueue.isEmpty() && i<intArray.length ){
            TreeNode currentTreeNode=treeNodeQueue.poll();
            TreeNode leftTreeNode=new TreeNode(intArray[i]);
            currentTreeNode.left=leftTreeNode;
            treeNodeQueue.offer(leftTreeNode);
            i++;
            // 因为要添加右子树-这里不加一个判断可能会导致越界
            if(i<intArray.length){
                TreeNode rightTreeNode=new TreeNode(intArray[i]);
                i++;
                currentTreeNode.right=rightTreeNode;
                treeNodeQueue.offer(rightTreeNode);
            }

        }
        return treeRootNode;

    }


    /**
     * 深度遍历-前序遍历  DFS -Parent-Left-Right
     * 后 放入一个数组中
     * @param treeNode
     */
    public static ArrayList<TreeNode> deepFirstSearchPLR(TreeNode treeNode, ArrayList<TreeNode> arrayList ){
        if(treeNode==null){
            return arrayList;
        }
        arrayList.add(treeNode);
        deepFirstSearchPLR(treeNode.left,arrayList);
        deepFirstSearchPLR(treeNode.right,arrayList);
        return arrayList;
    }

    /**
     * 获取一个树的最大深度
     * @param treeNode
     * @return
     */
    public static int getMaxDeep(TreeNode treeNode){
        if (treeNode==null){
            return 0;
        }
        return Math.max(getMaxDeep(treeNode.left),getMaxDeep(treeNode.right))+1;
    }

    /**
     * 广度优先遍历
     */
    public static void bfs(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        Queue<TreeNode> treeNodeQueue=new ArrayDeque<>();
        treeNodeQueue.offer(treeNode);
        while (!treeNodeQueue.isEmpty()){
            TreeNode temTreeNode=treeNodeQueue.poll();
            if (temTreeNode.left!=null){
                treeNodeQueue.offer(temTreeNode.left);
            }
            if(temTreeNode.right!=null){
                treeNodeQueue.offer(temTreeNode.right);
            }
            // doSomeThing(temTreeNode);
        }

    }
}
