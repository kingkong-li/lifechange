package com.jingang.lifechange.utils;

import android.widget.Toast;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime: 2021/7/4-10:12 AM
 * @changeTime:
 */
public class ToastUtil {
    private volatile static ToastUtil sInstance;
    private Toast mToast;
    private String mLastMessage;

    public ToastUtil() {
      mToast=Toast.makeText(MainApplication.getApplication(),mLastMessage,Toast.LENGTH_LONG);
    }

    public static ToastUtil getInstance() {
        if (sInstance == null) {
            synchronized (ToastUtil.class) {
                // 这里判空是因为第二个线程可能已经走完上句话，但是我们这边new 对象还未完成
//                至于为啥不加锁之后再判空，是为了性能，大部分时间是已经有单例的情况的
                if (sInstance == null) {
                    sInstance = new ToastUtil();
                }
            }
        }
        return sInstance;
    }
    public void show(String message){
        mLastMessage=message;
        mToast.setText(message);
        mToast.show();
    }
}
