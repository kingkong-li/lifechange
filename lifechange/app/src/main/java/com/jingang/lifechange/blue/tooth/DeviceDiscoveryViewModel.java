package com.jingang.lifechange.blue.tooth;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class DeviceDiscoveryViewModel {

    private final BtDiscoveryManager manager = new BtDiscoveryManager();
    private final MutableLiveData<List<BtPeer>> deviceLiveData = new MutableLiveData<>();

    public DeviceDiscoveryViewModel() {
        manager.setListener(new BtDiscoveryListener() {
            @Override
            public void onDeviceFound(BtPeer peer) {
                deviceLiveData.postValue(manager.getCachedDevices());
            }
        });
    }


    public void start(Context ctx) {
        manager.start();
    }


    public void stop(Context ctx) {
        manager.stop();
    }

    public MutableLiveData<List<BtPeer>> getNewDevices() {
        return deviceLiveData;
    }
}

