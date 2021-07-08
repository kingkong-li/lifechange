package com.jingang.lifechange.algorithm.struct.vector;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Stack;

/**
 * 有两个数组 nums1和nums2（互不重复），其中nums1是nums2的子集。
 * 在nums2的相应位置找到nums1所有元素的下一个更大数字。
 * nums1中的数字x的下一个更大数字是nums2中x右边第一个更大的数字。 如果它不存在，则为此数字输出-1。
 *
 * 输入: nums1 = [4,1,3], nums2 = [1,3,4,2]
 * 输出: [-1,3,4]
 * @Author: jingang.Li
 * @CreateTime: 2021/7/7-3:15 PM
 */
class NextBigger {
    /**
     * 首先拿到这个题 我们做分解，如果对nums1 每个元素 我们可以获得它的nextBigger 元素
     * 那么对nums1 只是做一次循环 可以得到。
     * 我们看到这种顺序问题，可以向栈或者队列思考下
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
//        创建一个hashMap用来存储数组中每个数的下一个更大的值
        int initNumber=(int)Math.round(nums2.length/0.75)+1;
        HashMap<Integer,Integer> hashMap=new HashMap<>(initNumber);
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<nums2.length;i++){
           while(!stack.isEmpty() && stack.peek()<nums2[i]){
               hashMap.put(stack.pop(),nums2[i]);
           }
           stack.push(nums2[i]);
        }
        int[] array=new int[nums1.length];
        for(int j=0;j<nums1.length;j++){
            array[j]=hashMap.getOrDefault(nums1[j],-1);
        }
        return array;

    }
}
