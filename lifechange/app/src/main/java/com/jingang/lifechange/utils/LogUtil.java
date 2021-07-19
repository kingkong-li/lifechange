package com.jingang.lifechange.utils;

import android.util.Log;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime: 2021/7/4-10:05 AM
 * @changeTime:
 */
public class LogUtil {
    public static void throwAError(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   int i=1;
                }catch (NullPointerException e){
                    Log.v("JG","catch inner");
                    e.printStackTrace();
                }finally {
                   String a="hahaha";
                }

            }
        }).start();

    }
}
