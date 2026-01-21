package com.jingang.lifechange.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Description: 线程池
 * @Author: kingkong
 * @CreateTime: 2020/8/25-2:23 PM
 * @changeTime:
 */
public class PublicThreadPools{

    public static ExecutorService getService(){
        return service;
    }
    private static final Handler MAIN_HANDLER =
            new Handler(Looper.getMainLooper());
    private  static ExecutorService service= Executors.newFixedThreadPool(5, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread=new Thread(r);
            Process.setThreadPriority(Process.THREAD_PRIORITY_DEFAULT);
            thread.setName("PublicThreadPools");
            return thread;
        }
    });
    public static void runOnUiThread(Runnable runnable){
        MAIN_HANDLER.post(runnable);
    }

}
