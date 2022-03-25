package com.jingang.lifechange.utils;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.jingang.lifechange.BuildConfig;
import com.jingang.lifechange.MainActivity;
import com.jingang.lifechange.widget.WidgetDataManager;


/**
 * @Description:
 * @Author:
 * @CreateTime:2020/8/2-5:14 PM
 * @changeTime:
 */
public class MainApplication extends Application {
    private static final String TAG = "lifeCycle:"+MainApplication.class.getName();
    private static MainApplication sInstance;

    @Override
    protected void attachBaseContext(Context base) {
        Log.v(TAG,"attachBaseContext");
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        Log.v(TAG,"onCreate");
        super.onCreate();
        sInstance=this;
        ActivityManager.start();
        initStrictModeConfig();
        WidgetDataManager.init(this);

    }

    private void initStrictModeConfig() {
        if(BuildConfig.DEBUG){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectCustomSlowCalls()//API等级11，使用StrictMode.noteSlowCode
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .detectActivityLeaks()
                    .setClassInstanceLimit(MainActivity.class,0)
                    .penaltyLog()
                    .build());
        }
    }



    public static MainApplication getApplication(){
        return sInstance;
    }

}
