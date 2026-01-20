package com.jingang.lifechange.blue.tooth;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class BtDeviceCache {

    private final Map<String, BtPeer> cache = new ConcurrentHashMap<>();

    public void put(BtPeer peer) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cache.merge(peer.address, peer, new BiFunction<BtPeer, BtPeer, BtPeer>() {
                @Override
                public BtPeer apply(BtPeer btPeerOld, BtPeer btPeerNew) {
                    return btPeerOld.update(btPeerNew.rssi);
                }
            });
        }
    }

    public List<BtPeer> snapshot() {
        return new ArrayList<>(cache.values());
    }

    public void clear() {
        cache.clear();
    }
}

