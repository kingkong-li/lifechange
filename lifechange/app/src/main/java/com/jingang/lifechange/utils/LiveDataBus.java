package com.jingang.lifechange.utils;

import androidx.lifecycle.MutableLiveData;

/**
 * @author lijingang
 * @description: 用户进程内通信，事件分发
 * @date
 */
public class LiveDataBus {
    public static final MutableLiveData<Integer> TEST_DATA_FLOW = new MutableLiveData<>();
}
