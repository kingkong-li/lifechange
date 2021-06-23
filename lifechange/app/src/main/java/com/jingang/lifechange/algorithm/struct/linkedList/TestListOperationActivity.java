package com.jingang.lifechange.algorithm.struct.linkedList;

import android.os.Bundle;
import android.util.Log;

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
        BaseListOperation.testMerge();
        String aa="nihao";
        String bb="nihao";
        Log.v("String", "aa==bb"+(aa==bb)); // 结果是true 唯一性 堆中常量区 String是唯一的
        String cc=new String("nihao");
        String dd=new String("nihao");
        Log.v("String","cc==dd"+(cc==dd)); // 结果是false 应该是放在堆中 有两份copy

        StringBuilder a=new StringBuilder();
        Log.v("String","String 占用几个byte="+aa.getBytes().length+" default size="+a.toString().length()+","+a.toString().getBytes());
    }
}