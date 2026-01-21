package com.jingang.lifechange.blue.tooth;

import androidx.annotation.NonNull;

public final class BtPeer {

    public final String address;
    public final String name;
    public final int rssi;
    public final long lastUpdateTime;

    public final String deviceBrand;

    public BtPeer(String address, String name, int rssi, long lastSeen, String deviceBrand) {
        this.address = address;
        this.name = name;
        this.rssi = rssi;
        this.lastUpdateTime = lastSeen;
        this.deviceBrand = deviceBrand;
    }

    public BtPeer update(int newRssi) {
        return new BtPeer(
                address,
                name,
                newRssi,
                System.currentTimeMillis(),
                deviceBrand
        );
    }

    @NonNull
    @Override
    public String toString() {
        return "BtPeer{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", rssi=" + rssi +
                ", lastSeen=" + lastUpdateTime +
                '}';
    }
}
