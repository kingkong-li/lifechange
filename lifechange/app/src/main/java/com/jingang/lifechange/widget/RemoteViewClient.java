package com.jingang.lifechange.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.jingang.lifechange.R;

/**
 * @Description: RemoteViews相当于远程View系统的数据源，也可以认为是客户端，
 * 请求远程View在服务器（桌面app 通知栏app等）
 * 上如何显示，同时服务器能够把远程view显示的一些情况通过广播，传递给我们app
 * @Author: jingang.Li
 * @CreateTime:2022/3/24-4:17 PM
 * @changeTime:
 */
public class RemoteViewClient {
    private static final String TAG = "RemoteViewClient";
    private RemoteViews mRemoteViews;

    public RemoteViews getRemoteViews(Context context) {
        if (mRemoteViews == null) {
            mRemoteViews = new RemoteViews(context.getPackageName(), R.layout.learn_app_widget);
        }
        return mRemoteViews;
    }

    public void requestUpdateWidget(Context context) {
        buildRemoteViewData(context);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName componentName = new ComponentName(context, MyAppWidgetProvider.class);
        appWidgetManager.updateAppWidget(componentName, getRemoteViews(context));
    }

    int i = 0;

    private void buildRemoteViewData(Context context) {

        addPlayOrPauseFeedBack(context);
        if (i == 0) {
            getRemoteViews(context).setImageViewResource(R.id.iv_banner, R.drawable.example_appwidget_preview);
            i = 1;
        } else {
            getRemoteViews(context).setImageViewResource(R.id.iv_banner, R.drawable.splash);
            i = 0;
        }
    }

    private void addPlayOrPauseFeedBack(Context context) {
        Intent playOrPauseIntent = new Intent(WidgetDataManager.ACTION_CLICK_IMAGE);
        playOrPauseIntent.setClassName(context.getPackageName(),
                "com.jingang.lifechange.widget.MyAppWidgetProvider");
        PendingIntent playOrPausePendingIntent = PendingIntent.getBroadcast(context, 0, playOrPauseIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        getRemoteViews(context).setOnClickPendingIntent(R.id.iv_banner, playOrPausePendingIntent);
    }
}
