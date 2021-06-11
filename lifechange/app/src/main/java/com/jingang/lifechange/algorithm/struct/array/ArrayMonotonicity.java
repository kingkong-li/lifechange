package com.jingang.lifechange.algorithm.struct.array;

import android.util.Log;

/**
 * @Description: 数组单调性专项练习
 * @Author: jingang.Li
 * @CreateTime: 2021/6/11-10:45 AM
 * @changeTime:
 */
class ArrayMonotonicity {
    private static final String TAG =ArrayMonotonicity.class.getSimpleName() ;
    private int[] mArray=new int[]{-1, -5, -10, -1100, -1100, -1101, -1102, -9001};
    public ArrayMonotonicity(){
        Log.v(TAG,"mArray 是单调的"+checkArrayMonotonicity(mArray));

    }

    /**
     * 检查数组是否是单调的
     * @param array
     * @return
     */
    public boolean checkArrayMonotonicity(int[] array){
        boolean isAsc=true;
        boolean isDes=true;
        for(int i=0;i<array.length-1;i++){

            if(isAsc && array[i]>array[i+1]){
                isAsc=false;

            }
            if(isDes && array[i]<array[i+1]){
                isDes=false;
            }
        }
        return isAsc || isDes;


    }

}
