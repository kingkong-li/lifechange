package com.jingang.lifechange.algorithm.struct.array;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 数组基础篇
 * 数组在内存中是一段连续存储空间
 * 数组是属于引用数据类型（数组名中存储的是内存首地址）；
 * 数组本身只有有length属性
 * 数组大小是固定的，在初始化之后它的size就固定了
 * @Author: jingang.Li
 * @CreateTime: 2021/6/10-9:35 AM
 *
 */
class BaseArrayOption {
    private static final String TAG =BaseArrayOption.class.getSimpleName() ;
    // 数组声明-首选-这种初始化 只能放这边
    private int[] arr2 = {1,2,3,4};
    // 数组声明-备选
    private int arr[]=new int[]{1,2,3};

     public BaseArrayOption(){
         init();
     }

    private void init(){
//        数组初始化
        arr=new int[]{2,3,4,5,8,9,10};
        Log.v(TAG,"init arr="+getIntArrayString(arr));

    }

    public void checkBaseOption(){
         //        数组填充-全部位置
        Arrays.fill(arr, 8);
        Log.v(TAG,"fill 8 arr="+getIntArrayString(arr));
//        数组填充-指定位置
        Arrays.fill(arr,0,3,100);
        Log.v(TAG,"fill 0-3 arr="+getIntArrayString(arr));
//      数组排序-升序
        Arrays.sort(arr);
        Log.v(TAG,"sort arr="+getIntArrayString(arr));
//       数组排序-指定起始点
        Arrays.sort(arr,1,3);
//        复制-产生新的数组
        int[] array=Arrays.copyOf(arr,2);
//        复制-指定起始位置
        int[] array1=Arrays.copyOfRange(array,1,4);
//      数组转换成列表-并检查是否包含其中某个元素
        List<int[]> arrayList=Arrays.asList(arr);
        Log.v(TAG,"arrayList.contains(1)="+arrayList.contains(1));

    }

    /**
     * 返回对象数组的String
     * @param array
     * @param <T> 泛型定义
     * @return
     */
    private <T> String getArrayString(T[] array){
         StringBuilder arrayString=new StringBuilder();
         for(int i=0;i<array.length;i++){
             arrayString.append(array[i]).append(" ");
         }
        return arrayString.toString();
    }

    public static String getIntArrayString(int[] array){
        StringBuilder arrayString=new StringBuilder();
        for (int value : array) {
            arrayString.append(value).append(" ");
        }
        return arrayString.toString();
    }




}
