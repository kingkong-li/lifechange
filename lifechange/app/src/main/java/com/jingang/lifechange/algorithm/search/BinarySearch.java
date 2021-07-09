package com.jingang.lifechange.algorithm.search;

import java.util.Arrays;

/**
 * @Description: array为有序数组，从array中找到目标值-target的位置
 * @Author: jingang.Li
 * @CreateTime: 2021/7/9-9:53 AM
 */
public class BinarySearch {
    public int findTargetIndex(int[] array,int target){
        if(array==null || array.length<1){
            return  -1;
        }
        int low=0;
        int high=array.length-1;

        while(low<=high){
            int mid=(low+high)/2;
            if(target==array[mid]){
                return mid;
            }else if(target<array[mid]){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return -1;
    }
}
