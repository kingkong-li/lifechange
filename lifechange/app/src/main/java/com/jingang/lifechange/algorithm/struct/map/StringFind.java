package com.jingang.lifechange.algorithm.struct.map;

import java.util.HashMap;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/7/4-11:09 AM
 * @changeTime:
 */
public class StringFind {
    public boolean contain(String[] stringArray,String targetString){
        if(stringArray==null || targetString==null || stringArray.length<1 ){
            return false;
        }
        int initialCapacity=(int)Math.round(stringArray.length*1.2)+1;
        HashMap<String,Integer> hashMap=new HashMap<>(initialCapacity);

        for(int i=0;i<stringArray.length;i++){
            hashMap.put(stringArray[i],i);
        }
        return hashMap.containsKey(targetString);
    }
}
