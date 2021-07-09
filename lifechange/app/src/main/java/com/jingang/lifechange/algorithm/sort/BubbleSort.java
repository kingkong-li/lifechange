package com.jingang.lifechange.algorithm.sort;

/**
 * @Description: 冒泡排序
 * @Author:
 * @CreateTime:2020/8/13-6:09  PM
 * @changeTime:
 */
public class BubbleSort implements Sort {
    @Override
    public int[] ascendingSort(int[] intArray) {
        for(int number=intArray.length-1;number>1;number--){
            for(int i=0;i<number;i++){
                if(intArray[i]>intArray[i+1]){
                    int temp=intArray[i+1];
                    intArray[i+1]=intArray[i];
                    intArray[i]=temp;
                }
            }
        }

        return intArray;
    }

    @Override
    public int[] descendingSort(int[] intArray) {
        return intArray;
    }
}
