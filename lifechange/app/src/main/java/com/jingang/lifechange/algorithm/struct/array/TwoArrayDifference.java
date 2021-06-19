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
    /**
     *  获取连个数组中 差最小的两个数字
     *  采用暴力解法-两个循环搞定 时间复杂度为O(N^2)
     * @param array1
     * @param array2
     * @return
     */
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

    /**
     *  求两个数组中差值最小的两个数、
     *  首先对数组进行排序-数组排序 数组排序是采用快速排序算法 时间复杂度是O(n log(n))
     *  然后再通过一次循环，从头部开始比较两个数组对应元素大小，谁如果比较小，就去index+1
     *  去靠近大的，通一次循环-就可以得到两个差值最近的元素
     * @param array1
     * @param array2
     * @return
     */
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
