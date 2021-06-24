package com.jingang.lifechange.base;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.R;

import java.util.ArrayList;

/**
 * 目前发现项目中用到太多类似于列表的页面
 * 然后 每个列表项 会有一个名称 点击名称会跳转到对应的Activity
 *
 *
 */
public class BaseListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);
        RecyclerView recyclerView=findViewById(R.id.activity_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyRecyclerViewAdapter(getActivityList()));

    }
    protected ArrayList<ActivityInfo> getActivityList(){
        return new ArrayList<>();
    }
}