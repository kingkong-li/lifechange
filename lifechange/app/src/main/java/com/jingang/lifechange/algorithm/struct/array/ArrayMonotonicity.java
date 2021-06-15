package com.jingang.lifechange.algorithm.struct.array;

import android.util.Log;

import java.util.Arrays;

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

    /**
     * 发现数组中最高的波--最高这样定义 左边都递增 右边都递减
     * 非严格递增 找到第一个最高峰就好
     * 暴力解法
     * @param array
     * @return
     */
    public int[] findPeakInArray(int[] array){
        int peak=0;
        int peakIndex=0;
        for(int i=0;i<array.length;i++) {
            int peakI = 1;
            for (int j = i; j > 0; j--) {
                if (array[j] >= array[j - 1]) {
                    peakI++;
                } else {
                    break;
                }
            }
            for (int k = i; k < array.length - 1; k++) {
                if (array[k] >= array[k + 1]) {
                    peakI++;
                } else {
                    break;
                }
            }
            if (peakI > peak) {
                peak = peakI;
                peakIndex = i;
            }
        }
        return new int[]{peak,peakIndex};
    }

    public int[] findPeakInArray2(int[] array){
        int[] leftPeakArray=findLeftPeakArray(array);
        int[] rightPeakArray=findRightPeakArray(array);
        int maxLength=0;
        int peakIndex=0;
        for(int i=0;i<array.length;i++){
            if(leftPeakArray[i]+rightPeakArray[i]+1>maxLength){
                maxLength=leftPeakArray[i]+rightPeakArray[i]+1;
                peakIndex=i;
            }
        }

       return new int[]{maxLength,peakIndex};
    }

    private int[] findRightPeakArray(int[] array) {
        int[] rightArray=new int[array.length];
        Log.v(TAG,"right init="+BaseArrayOption.getIntArrayString(rightArray));
//         最后得到结果是一个全部为零的数组 这个证明了 基本类型声明后就是0  对象声明后 如果不初始化就是null
//        那这又是为啥呢？
        for(int i=array.length-2;i>=0;i--){
            if(array[i]>=array[i+1]){
                rightArray[i]=rightArray[i-1]+1;
            }
        }

        return rightArray;
    }

    private int[] findLeftPeakArray(int[] array) {
        int[] leftArray=new int[array.length];
        for(int i=1;i<array.length;i++){
            if(array[i]>=array[i-1]){
                leftArray[i]=leftArray[i-1]+1;
            }
        }

        return leftArray;
    }

}
