package com.jingang.lifechange.algorithm.struct.array;

import java.util.Arrays;

/**
 * @Description: 最短无序子数组
 * 给定一个整数数组，找到一个连续子数组。按升序对这个子数组进行排序，会使整个数组呈现升序。返回最短子数组的 起始点
 * 输入: array = [1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19]
 * 输出: [3, 9]
 * @Author: jingang.Li
 * @CreateTime: 2021/6/19-10:13 AM
 */
class ShortestDisorderArray {
    /**
     * 找到最短的无序子数组
     * 思路：首先把原来的数组复制-然后按升序排列
     * 首先从前向后对比-原数组如果某一个值比升序数组大，那么这个值就应该跟后面的调整
     * 所以这个值肯定就起点
     * 然后从后到前对比 如果原数组有一个值比升序数组小 那么肯定有一个大的值 在原数组前面，那么这里应该也需要调整
     * @param array
     * @return
     */
    public int[] findShortestDisorderSubArray(int[] array){
        int startIndex=0;
        int endIndex=array.length-1;
        int[] ascArray= Arrays.copyOf(array,array.length);
        Arrays.sort(ascArray);
        for(int i=0;i<array.length;i++){
            if(array[i]>ascArray[i]){
                startIndex=i;
                break;
            }
        }
        for(int j=array.length-1;j>=0;j--){
            if(array[j]<ascArray[j]){
                endIndex=j;
                break;
            }
        }

        return new int[]{startIndex,endIndex};
    }

    /**
     * 这题的关键在于找到
     * 最短未排序数组的最小值 和最大值
     * 最短未排序数组的最小值，有个特点 就是它肯定比他前后的都小
     * 最短未排序数组的最大值 有个特点 就是他肯定比它前后的数组都大
     * 已经排序数组的特点是 任意一个值都比前面的大 比后面的小
     * @param array
     * @return
     */
    public int[] findShortestDisorderSubArray2(int[] array){
        // 寻找未排序的最大值和最小值
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<array.length;i++){
            if(!isOrdered(array,i)){
                min=Math.min(min,array[i]);
                max=Math.max(max,array[i]);
            }
        }

        int startIndex=0;
        int endIndex=array.length-1;


        while (array[startIndex]<=min){
            startIndex++;
        }
        while (array[endIndex]>=max){
            endIndex--;
        }
        return new int[]{startIndex,endIndex};


    }

    /**
     * 这个方法主要是判断正常的排序
     * @param array
     * @param i
     * @return
     */
    private boolean isOrdered(int[] array,int i){
        if(i==0){
            return array[0]<=array[1];
        }
        if(i==array.length-1){
            return array[i-1]<=array[i];
        }
        return array[i-1]<=array[i] && array[i]<=array[i+1];
    }
}
