package com.jingang.lifechange.algorithm.struct;

import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 给出一个整数数组nums和一个target整数，返回两个和为target的整数
 * @Author: jingang.Li
 * @CreateTime: 2021/5/28-9:08 AM
 * @changeTime:
 */
public class TwoSum {
    /**
     *
     * @param numbers int整型一维数组
     * @param target 目标值
     * @return 唯一一组数组下标
     * 如果需要输出所有解 可以通过 ArrayList<int[]> arrayList= new ArrayList<int[]>();
     * 如果需要下标则可以简单的转换下
     */
    private static int[] twoSum (int[] numbers, int target) {
        if(numbers==null || numbers.length<2){
            return new int[0];
        }
        HashMap<Integer, Integer> map=new HashMap<>(numbers.length);
        for(int i=0;i<numbers.length;i++){
            if(map.containsKey(target-numbers[i])){
                return new int[]{target-numbers[i],numbers[i]};
            }
            map.put(numbers[i],i);
        }

        return new int[0];
    }

    /**
     * 通过set来解决两数之和的问题，但是如果需要求下标 则需要用map
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] numbers, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : numbers) {
            int potentialMatch = target - num;
            if (set.contains(potentialMatch)) {
                return new int[]{potentialMatch, num};
            } else {
                set.add(num);
            }
        }
        return new int[0];
    }

    /**
     * 通过两个指针方法来解决 两数之和问题
     * 基本思想：升序数组两数之和 可以这样调整：
     * 通过两个指针来调整
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] numbers, int target) {
        Arrays.sort(numbers);
        int left=0;
        int right=numbers.length-1;
        while(left<right){
            if(numbers[left]+numbers[right]==target){
                return new int[]{numbers[left],numbers[right]};
            }else if(numbers[left]+numbers[right]>target){
                right--;
            }else {
                left++;
            }
        }
        return new int[0];
    }

    public static void testTwoSum () {
        int[] numbers=new int[]{1,2,3,4};
        Log.v("twoSum","输入数组="+getArrayString(numbers));

        int target=5;
        Log.v("twoSum","输入target="+""+target);
        Log.v("twoSum","方法一 输出结果="+getArrayString(twoSum(numbers,target)));
        Log.v("twoSum","方法二 输出结果="+getArrayString(twoSum1(numbers,target)));
        Log.v("twoSum","方法三 输出结果="+getArrayString(twoSum2(numbers,target)));
    }

    private static  String getArrayString(int[] array){
        StringBuilder arrayString= new StringBuilder("[");
        for(int i=0;i<array.length; i++){
            if(i==0){
                arrayString.append(array[0]);
            }else{
                arrayString.append(",").append(array[i]);
            }
        }
        return arrayString+"]";

    }


}
