package com.jingang.lifechange.socket;

import android.content.Context;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.jingang.lifechange.utils.MainApplication;

import java.util.function.Consumer;

public class HotspotHelper {

    private final WifiManager wifiManager;
    private WifiManager.LocalOnlyHotspotReservation reservation;
    private volatile static HotspotHelper sInstance;

    public HotspotHelper() {
        wifiManager = (WifiManager) MainApplication.getApplication().getSystemService(Context.WIFI_SERVICE);
    }
    public static HotspotHelper getInstance() {
        if (sInstance == null) {
            synchronized (HotspotHelper.class) {
                // 这里判空是因为第二个线程可能已经走完上句话，但是我们这边new 对象还未完成
//                至于为啥不加锁之后再判空，是为了性能，大部分时间是已经有单例的情况的
                if (sInstance == null) {
                    sInstance = new HotspotHelper();
                }
            }
        }
        return sInstance;
    }



    public void startHotspot(Consumer<SoftApConfiguration> onStarted) {
        wifiManager.startLocalOnlyHotspot(
                new WifiManager.LocalOnlyHotspotCallback() {

                    @Override
                    public void onStarted(WifiManager.LocalOnlyHotspotReservation res) {
                        reservation = res;
                        SoftApConfiguration  config = res.getSoftApConfiguration();
                        onStarted.accept(config);
                    }

                    @Override
                    public void onFailed(int reason) {
                        Log.e("Hotspot", "Failed reason=" + reason);
                    }
                },
                new Handler(Looper.getMainLooper())
        );
    }

    public void stopHotspot() {
        if (reservation != null) {
            reservation.close();
            reservation = null;
        }
    }

    /**
     * 通过Reservation实例判断热点是否开启
     * @return true=开启，false=关闭
     */
    public boolean isHotspotActive() {
        // 核心逻辑：Reservation不为空且未被关闭，则热点处于开启状态
        return reservation != null;
    }
}

