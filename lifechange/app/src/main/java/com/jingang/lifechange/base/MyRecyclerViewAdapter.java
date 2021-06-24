package com.jingang.lifechange.base;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.MainActivity;
import com.jingang.lifechange.R;
import com.jingang.lifechange.generics.TestGenericsActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/6/23-9:29 PM
 * @changeTime:
 */
class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

    private volatile ArrayList<ActivityInfo> mData;
    public MyRecyclerViewAdapter(ArrayList<ActivityInfo> data){
        mData=data;
    }
    public void setData(ArrayList<ActivityInfo> data){
        mData=data;
    }

    @NonNull
    @NotNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull final MyHolder holder, final int position) {
        Log.v("JG",mData.get(position).labelName);
       holder.button.setText(mData.get(position).labelName);
       holder.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent();
               intent.setClassName(holder.itemView.getContext(), mData.get(position).className);
               holder.itemView.getContext().startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        Button button;
        public MyHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.bt_go_activity);
        }
    }
}
