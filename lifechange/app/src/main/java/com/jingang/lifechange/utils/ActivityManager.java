package com.jingang.lifechange.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime: 2021/7/5-5:54 PM
 * @changeTime:
 */
public class ActivityManager {
    private static Activity sCurrentActivity;

    public  static  void start() {
        MainApplication.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                sCurrentActivity=activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public static Activity getCurrentActivity(){
        return sCurrentActivity;
    }

}
