package com.jingang.lifechange.utils;

import android.app.Application;



/**
 * @Description:
 * @Author:
 * @CreateTime:2020/8/2-5:14 PM
 * @changeTime:
 */
public class MainApplication extends Application {
    private static MainApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
        ActivityManager.start();
    }

    public static MainApplication getApplication(){
        return sInstance;
    }

}
