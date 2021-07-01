package com.jingang.lifechange.algorithm.struct.tree;

import com.jingang.lifechange.algorithm.struct.tree.bean.TreeNode;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/7/1-11:12 AM
 * @changeTime:
 */
class BaseTreeOperation {
    /**
     * 深度遍历-前序遍历 Parent-Left-Right
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
}
