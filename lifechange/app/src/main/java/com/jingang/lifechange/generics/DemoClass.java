package com.jingang.lifechange.generics;

import android.util.Log;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/6/9-5:05 PM
 * @changeTime:
 */
class DemoClass extends BaseGenericClass<Integer> {
    @Override
    public void print(Integer data){
        Log.v("DemoClass",String.valueOf(data));
    }
}
