package com.jingang.lifechange.generics;

import android.util.Log;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/6/9-4:57 PM
 * @changeTime:
 */
public class BaseGenericClass<T> {
    public void print(T data){
        Log.v("DemoClass",String.valueOf(data));
    }

}
