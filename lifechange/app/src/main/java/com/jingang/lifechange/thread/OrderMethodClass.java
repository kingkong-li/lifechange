package com.jingang.lifechange.thread;

import android.util.Log;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/7/8-1:09 PM
 * @changeTime:
 */
public class OrderMethodClass {
    public static final String TAG=OrderMethodClass.class.getSimpleName();
    private final Object lock1=new Object();
    private final Object lock2=new Object();
    private volatile boolean method1HadRun=false;
    private volatile boolean method2hadRun=false;
    public void method1(){
        synchronized (lock1){
            Log.v(TAG,"method1 running");
            lock1.notifyAll();
            method1HadRun=true;
        }

    }
    public void method2(){
        synchronized (lock1){
            if(!method1HadRun){
                try {
                    lock1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock2){
                Log.v(TAG,"method2 running");
                method2hadRun=true;
                lock2.notifyAll();
            }
        }

    }
    public void method3(){
        synchronized (lock2){
            if(!method2hadRun){
                try {
                    lock2.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.v(TAG,"method3 running");
        }
    }
}
