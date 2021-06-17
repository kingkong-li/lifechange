package com.jingang.lifechange.algorithm.struct.array;

import java.util.Arrays;

/**
 * @Description: 给定两个数组，找到一对数字（两个数组各取一个），他们的差值最小。返回这两个数字。
 * 输入:
 * arrayOne = [-1, 5, 10, 20, 28, 3]
 * arrayTwo = [26, 134, 135, 15, 17]
 * 输出: [28, 26]
 * @Author: jingang.Li
 * @CreateTime:2021/6/17-10:32 AM
 * @changeTime:
 */
class TwoArrayDifference {
    public int[] getMinDifference0(int[] array1,int[] array2){
        int index1=0;
        int index2=0;
        int minDiff=Integer.MAX_VALUE;
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array2.length;j++){
                int diffIJ=Math.abs(array1[i]-array2[j]);
                if(diffIJ<minDiff){
                    minDiff=diffIJ;
                    index1=i;
                    index2=j;
                }
            }
        }
        return  new int[]{array1[index1],array2[index2]};

    }

    public int[] getMinDifference1(int[] array1,int[] array2){
        Arrays.sort(array1);
        Arrays.sort(array2);
        int index1=0;
        int index2=0;
        int minDiff=Integer.MAX_VALUE;
        int i=0;
        int j=0;
        while (i<array1.length && j<array2.length){
            if(Math.abs(array1[i]-array2[j])<minDiff){
                minDiff=Math.abs(array1[i]-array2[j]);
                index1=i;
                index2=j;
            }else if(array1[i]<array2[j]){
                i++;
            }else{
                j++;
            }
        }
        return new int[]{array1[index1],array2[index2]};
    }
}
