package com.jingang.lifechange.blue.tooth;

import androidx.annotation.NonNull;

public class BlueDeviceMessage {
    public String deviceName;
    public String deviceAddress;
    public String data;

    @NonNull
    @Override
    public String toString() {
        return "BlueDeviceMessage{" +
                "deviceName='" + deviceName + '\'' +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", message='" + data + '\'' +
                '}';
    }
}
