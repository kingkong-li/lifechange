package com.jingang.lifechange.utils;

import androidx.lifecycle.MutableLiveData;

import com.jingang.lifechange.blue.tooth.BlueDeviceMessage;

/**
 * @author lijingang
 * @description: 用户进程内通信，事件分发
 * @date
 */
public class LiveDataBus {
    public static final MutableLiveData<Integer> TEST_DATA_FLOW = new MutableLiveData<>();

    public static final MutableLiveData<BlueDeviceMessage> BLUE_MESSAGE = new MutableLiveData<>();
}
