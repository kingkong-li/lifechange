package com.jingang.lifechange.blue.tooth;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jingang.lifechange.R;
import com.jingang.lifechange.utils.TimeUtil;

import java.util.Comparator;
import java.util.List;

public class BlueListAdapter extends RecyclerView.Adapter<BlueListAdapter.MyViewHolder> {

    private final String TAG = "BlueListAdapter";
    private volatile List<BtPeer> mData;
    private ItemSelectListener mOnClickListener;

    public BlueListAdapter() {
    }


    public void setData(List<BtPeer> data) {
        mData = data;
        sortBtPeers(mData);
        notifyDataSetChanged();
    }

    public void setOnClickListener(ItemSelectListener listener) {
        this.mOnClickListener = listener;
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
        holder.mLastUpdateTime.setText("最后更新时间："+ TimeUtil.formatTimestamp(peer.lastUpdateTime));
        holder.mDeviceVendorName.setText("蓝牙设备厂商："+ peer.deviceBrand);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "onClick blueDevice="+peer.address);
                mOnClickListener.onItemSelect(peer);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView mDeviceName;
        private final TextView mDeviceAddress;
        private final TextView mDeviceRssi;

        private final TextView mLastUpdateTime;
        private final TextView mDeviceVendorName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDeviceName = itemView.findViewById(R.id.blue_device_name);
            mDeviceAddress = itemView.findViewById(R.id.blue_device_mac_address);
            mDeviceRssi = itemView.findViewById(R.id.blue_device_rssi);
            mLastUpdateTime = itemView.findViewById(R.id.last_update_time);
            mDeviceVendorName = itemView.findViewById(R.id.vendor_name);

        }
    }
    // 使用静态Comparator避免重复创建
    public void sortBtPeers(List<BtPeer> peerList) {
        peerList.sort(
                // 第一优先级：名字是否有值 (有名字的排前面)
                Comparator.comparing((BtPeer p) -> p.name == null || p.name.isEmpty())
                        // 第二优先级：RSSI 信号强度 (数值越大代表信号越强，所以用降序)
                        .thenComparing(Comparator.comparingInt((BtPeer p) -> p.rssi).reversed())
        );
    }
}
