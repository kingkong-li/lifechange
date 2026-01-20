package com.jingang.lifechange.blue.tooth;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.R;

import java.util.List;

public class BlueListAdapter extends RecyclerView.Adapter<BlueListAdapter.MyViewHolder> {

    private final String TAG = "BlueListAdapter";
    private volatile List<BtPeer> mData;

    public BlueListAdapter() {
    }


    public void setData(List<BtPeer> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blue_device_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.v(TAG, "onBindViewHolder");
        BtPeer peer = mData.get(position);
        holder.mDeviceName.setText("蓝牙设备名称："+peer.name);
        holder.mDeviceAddress.setText("蓝牙设备Mac地址："+peer.address);
        holder.mDeviceRssi.setText("蓝牙设备信号强度："+ peer.rssi);

    }

    @Override
    public int getItemCount() {
        int count = mData == null ? 0 : mData.size();
        Log.v(TAG, "getItemCount:" + count);
        return count;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView mDeviceName;
        private final TextView mDeviceAddress;
        private final TextView mDeviceRssi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDeviceName = itemView.findViewById(R.id.blue_device_name);
            mDeviceAddress = itemView.findViewById(R.id.blue_device_mac_address);
            mDeviceRssi = itemView.findViewById(R.id.blue_device_rssi);
        }
    }
}
