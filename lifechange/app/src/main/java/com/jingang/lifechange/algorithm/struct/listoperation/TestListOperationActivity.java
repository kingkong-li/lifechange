package com.jingang.lifechange.algorithm.struct.listoperation;

import android.os.Bundle;

import com.jingang.lifechange.R;
import com.jingang.lifechange.base.BaseActivity;

/**
 * @Description: 列表操作测试Activity
 * @author jingang
 *
 */
public class TestListOperationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        BaseListOperation.testReverseList();
        BaseListOperation.TestDoubleLinkList();
    }
}