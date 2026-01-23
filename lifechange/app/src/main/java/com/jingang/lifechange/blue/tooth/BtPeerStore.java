package com.jingang.lifechange.blue.tooth;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class BtPeerStore {

    private static final String SP_NAME = "bt_peer_store";
    private static final String KEY = "peers";
    private static final int MAX_SIZE = 5;

    private static volatile BtPeerStore sInstance;

    private final SharedPreferences sp;

    private BtPeerStore(Context context) {
        sp = context
                .getApplicationContext()
                .getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static BtPeerStore get(Context context) {
        if (sInstance == null) {
            synchronized (BtPeerStore.class) {
                if (sInstance == null) {
                    sInstance = new BtPeerStore(context);
                }
            }
        }
        return sInstance;
    }

    /** 添加或更新一个 BtPeer（按 address 去重） */
    public synchronized void add(BtPeer peer) {
        List<BtPeer> list = load();

        // 去重：同 address 认为是同一个设备
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).address.equals(peer.address)) {
                list.remove(i);
            }
        }

        if (list.size() >= MAX_SIZE) {
            list.remove(0);
        }

        list.add(peer);
        save(list);
    }

    /** 获取全部 BtPeer（按添加顺序） */
    public synchronized List<BtPeer> getAll() {
        return load();
    }

    /** 获取最近一次的设备（没有返回 null） */
    public synchronized BtPeer getLast() {
        List<BtPeer> list = load();
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    // ---------------- 内部实现 ----------------

    private List<BtPeer> load() {
        List<BtPeer> list = new ArrayList<>();
        String json = sp.getString(KEY, null);

        if (json == null || json.isEmpty()) {
            return list;
        }

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);

                list.add(new BtPeer(
                        o.getString("address"),
                        o.optString("name", null),
                        o.optInt("rssi", 0),
                        o.optLong("lastUpdateTime", 0),
                        o.optString("deviceBrand", null)
                ));
            }
        } catch (JSONException e) {
            // SP 数据损坏，兜底清空
            sp.edit().remove(KEY).apply();
        }

        return list;
    }

    private void save(List<BtPeer> list) {
        JSONArray array = new JSONArray();

        for (BtPeer p : list) {
            JSONObject o = new JSONObject();
            try {
                o.put("address", p.address);
                o.put("name", p.name);
                o.put("rssi", p.rssi);
                o.put("lastUpdateTime", p.lastUpdateTime);
                o.put("deviceBrand", p.deviceBrand);
            } catch (JSONException ignored) {}
            array.put(o);
        }

        sp.edit().putString(KEY, array.toString()).apply();
    }
}

