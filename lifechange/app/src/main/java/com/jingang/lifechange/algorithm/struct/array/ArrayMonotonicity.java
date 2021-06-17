package com.jingang.lifechange.algorithm.struct.array;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

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
     * 暴力解法... 时间复杂度O（N^2）
     * @param array
     * @return 返回一个数组，数组第一个值是这个长度，第二个值为峰顶元素的index
     */
    public int[] findPeakInArray1(@NotNull int[] array){
        int peak=0;
        int peakIndex=0;
        for(int i=0;i<array.length;i++) {
            int peakI = 1;//peadI 为当前i为峰顶的峰长度
            // 以当前i为峰顶，向前找单调递减的最低点
            for (int j = i; j > 0; j--) {
                if (array[j] >= array[j - 1]) {
                    peakI++;
                } else {
                    break;
                }
            }
            // 以当前i为峰顶，向后找单调递减的最低点
            for (int k = i; k < array.length - 1; k++) {
                if (array[k] >= array[k + 1]) {
                    peakI++;
                } else {
                    break;
                }
            }
//            如果 当期的峰长度大于，已知最大峰 将当前峰设置为最大峰
            if (peakI > peak) {
                peak = peakI;
                peakIndex = i;
            }
        }
        return new int[]{peak,peakIndex};
    }

    /**
     * 找到数组中最长的山-方法二
     * @param array
     * @return
     */
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


    /**
     * 找出每个元素的左边峰长度，
     * 这里有个算法就是-如果计算当前元素左峰长度的时候，
     * 有两种情况，一 当期元素 比上一个大-那么当前左峰高度是前一个+1，如果当期比上一个小，左峰高度是0
     * @param array
     * @return
     */
    private int[] findLeftPeakArray(int[] array) {
        int[] leftArray=new int[array.length];
        for(int i=1;i<array.length;i++){
            if(array[i]>=array[i-1]){
                leftArray[i]=leftArray[i-1]+1;
            }
        }

        return leftArray;
    }

    private int[] findRightPeakArray(int[] array) {
        int[] rightArray=new int[array.length];
        Log.v(TAG,"right init="+BaseArrayOption.getIntArrayString(rightArray));
//         最后得到结果是一个全部为零的数组 这个证明了 基本类型声明后就是0  对象声明后 如果不初始化就是null
//        那这又是为啥呢？
        for(int i=array.length-2;i>=0;i--){
            if(array[i]>=array[i+1]){
                rightArray[i]=rightArray[i+1]+1;
            }
        }

        return rightArray;
    }

    /**
     *这个理论基于，当前峰的最后一个元素，才是下一个峰的开始元素
     * 所以这个方法 只能求 真正单调的峰
     * @param array
     * @return
     */
    public int[] findPeakInArray3(int[] array){
        int maxLength=0;
        int peakIndex=0;
        int end=0;
        while (end<array.length){
            // 先寻找波峰
//            第一波 标记下当前起点
            int start=end;
            while(end+1<array.length && array[end+1]>array[end]){
                end++;
            }
            int peak=end;
            while (end+1<array.length && array[end+1]<array[end]){
                end++;
            }
            if(end>start && end-start>maxLength){
               maxLength=end-start+1;
               peakIndex=peak;
            }
            if(end==start){
                end++;
            }

        }
        return new int[]{maxLength,peakIndex};

    }


}
