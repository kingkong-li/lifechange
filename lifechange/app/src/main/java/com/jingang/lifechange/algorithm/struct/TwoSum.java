package com.jingang.lifechange.algorithm.struct;

import android.util.ArraySet;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
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
    public static void testTwoSum () {
        int[] numbers=new int[]{1,2,3,4};
        Log.v("twoSum","输入数组="+getArrayString(numbers));

        int target=5;
        Log.v("twoSum","输入target="+""+target);
        Log.v("twoSum","输出结果="+getArrayString(twoSum(numbers,target)));
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
