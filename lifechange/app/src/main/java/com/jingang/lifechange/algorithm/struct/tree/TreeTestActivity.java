package com.jingang.lifechange.algorithm.struct.tree;

import android.os.Bundle;

import com.jingang.lifechange.R;
import com.jingang.lifechange.algorithm.struct.tree.bean.TreeNode;
import com.jingang.lifechange.base.BaseActivity;

import java.util.ArrayList;

public class TreeTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_test);
        TreeNode treeNode= BaseTreeOperation.generateTree();
        ArrayList<TreeNode> treeNodeArrayList=new ArrayList<>();
        BaseTreeOperation.deepFirstSearchPLR(treeNode,treeNodeArrayList);
    }


}