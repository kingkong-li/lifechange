package com.jingang.lifechange.algorithm.struct.tree;

import android.os.Bundle;
import android.util.Log;

import com.jingang.lifechange.R;
import com.jingang.lifechange.algorithm.struct.tree.bean.TreeNode;
import com.jingang.lifechange.base.BaseActivity;

import java.util.ArrayList;

public class TreeTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_test);
        TreeNode treeNode= BaseTreeOperation.generateTree(new int[]{1,2,3,4,5,6,7,8,9,10,11,13});
        ArrayList<TreeNode> treeNodeArrayList=new ArrayList<>();
        BaseTreeOperation.deepFirstSearchPLR(treeNode,treeNodeArrayList);
        Log.v(getTag(),"treeNode number ="+
                new CompleteBinaryTree().calculateNodes(treeNode));
        Log.v(getTag(),"treeNode number ="+
                new CompleteBinaryTree().calculateNodes2(treeNode,1));
    }


}