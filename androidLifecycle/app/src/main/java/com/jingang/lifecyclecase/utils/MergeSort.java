package com.jingang.lifecyclecase.utils;

import java.util.Arrays;

/**
 * @Description:
 * @Author:
 * @CreateTime:2020/8/16-9:31 AM
 * @changeTime:
 */
public class MergeSort implements Sort {
    @Override
    public int[] ascendingSort(int[] intArray) {
        mergeSort(intArray);
        return intArray;
    }

    public  void mergeSort(int[] array){

        int length=array.length;

        int middle=length/2;

//   最后就是长度为2的数组排序好
        if(length>1){
            //拷贝数组array的左半部分
            int[]left=Arrays.copyOfRange(array,0,middle);
            //拷贝数组array的右半部分
            int[]right= Arrays.copyOfRange(array,middle,length);
            //递归array的左半部分
            mergeSort(left);
            //递归array的右半部分
            mergeSort(right);
            //数组左半部分、右半部分合并到Array
            merge(array,left,right);
        }
    }

    private  void merge(int[]result,int[]left,int[]right){

        int i=0,l=0,r=0;

//        选择最小的
        while(l<left.length&&r<right.length){
            if(left[l]<right[r]){
                result[i]=left[l];
                i++;
                l++;
            }else{
                result[i]=right[r];
                i++;
                r++;
            }
        }

        //如果右边剩下合并右边的
        while(r<right.length){
            result[i]=right[r];
            r++;
            i++;
        }

        while(l<left.length){
            result[i]=left[l];
            l++;
            i++;
        }
    }

    @Override
    public int[] descendingSort(int[] intArray) {
        return new int[0];
    }
}
