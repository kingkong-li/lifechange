package com.jingang.lifechange.widget;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @Description: 管理widget所有数据、更新、事件
 * @Author: jingang.Li
 * @CreateTime: 2022/3/24-4:13 PM
 * @changeTime:
 */
public class WidgetDataManager {
    private static final String TAG = WidgetDataManager.class.getSimpleName();
    public static final String ACTION_CLICK_IMAGE = "action_life_change_click_image";
    private static volatile WidgetDataManager sInstance;
    private Context mContext;
    private RemoteViewClient mRemoteViewClient;

    WidgetDataManager(Application application) {
        mContext = application.getApplicationContext();
        mRemoteViewClient = new RemoteViewClient();

    }

    public static WidgetDataManager getInstance() {
        if (sInstance == null) {
            Log.e("WidgetDataManager", "not init error");
            throw new NullPointerException();
        }
        return sInstance;
    }

    public static void init(Application application) {
        if (sInstance == null) {
            synchronized (WidgetDataManager.class) {
                if (sInstance == null) {
                    sInstance = new WidgetDataManager(application);
                }
            }
        }
    }

    public void onReceiveUpdateIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Log.i(TAG, "onReceiveUpdateIntent action=null");
        }
        String action = intent.getAction();
        Log.i(TAG, "onReceiveUpdateIntent action=" + action);
        switch (action) {
            case WidgetDataManager.ACTION_CLICK_IMAGE:
                mRemoteViewClient.requestUpdateWidget(mContext);
                break;
            case AppWidgetManager.ACTION_APPWIDGET_UPDATE:
                break;
            default:
                mRemoteViewClient.requestUpdateWidget(mContext);
                break;
        }


    }


}
