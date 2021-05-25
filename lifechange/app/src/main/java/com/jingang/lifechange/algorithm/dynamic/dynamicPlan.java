package com.jingang.lifechange.algorithm.dynamic;

import android.util.Log;

/**
 * @Description: 动态规划
 * @Author:
 * @CreateTime:2020/8/17-10:02 AM
 * @changeTime:
 */
public class dynamicPlan {
    public static  boolean findTargetString(char[][] twoDeString,String targetString){
        
            for(int i=0;i<twoDeString.length;i++){
                for(int j=0;j<twoDeString[i].length;j++){
                    if(targetString.charAt(0)==twoDeString[i][j]){
                       if(findNext(i,j,targetString.substring(1),twoDeString)){
                           return true;
                       }
                    }
                }
            }
            
        return false;
    }

    private  static boolean findNext(int targetI,int targetJ,String targetString,char[][] charArray){
        Log.e("findNext","targetI="+targetI+", targetJ="+targetJ+", targetString="+targetString+", charArray="+charArray.toString());

        if(targetString.length()==1){
            if(targetI-1>=0){
                if(charArray[targetI-1][targetJ]==targetString.charAt(0)){
                   return true;
                }

            }
            if(targetI+1<charArray.length){
                if(charArray[targetI+1][targetJ]==targetString.charAt(0)){
                    return true;

                }

            }
            if(targetJ+1<charArray[targetI].length){
                if(charArray[targetI][targetJ+1]==targetString.charAt(0)){
                    return true;

                }

            }
            if(targetJ-1>=0){
                if(charArray[targetI][targetJ-1]==targetString.charAt(0)){
                    return true;

                }

            }
        }else{
            if(targetI-1>=0){
                if(charArray[targetI-1][targetJ]==targetString.charAt(0)){

                    String nextString=targetString.substring(1);
                     return  findNext(targetI-1,targetJ,nextString,charArray);

                }

            }
            if(targetI+1<charArray.length){
                if(charArray[targetI+1][targetJ]==targetString.charAt(0)){
                    String nextString=targetString.substring(1);
                    return findNext(targetI+1,targetJ,nextString,charArray);

                }

            }
            if(targetJ+1<charArray[targetI].length){
                if(charArray[targetI][targetJ+1]==targetString.charAt(0)){
                    String nextString=targetString.substring(1);
                    return findNext(targetI,targetJ+1,nextString,charArray);

                }

            }
            if(targetJ-1>=0){
                if(charArray[targetI][targetJ-1]==targetString.charAt(0)){
                    String nextString=targetString.substring(1);
                    return findNext(targetI,targetJ-1,nextString,charArray);

                }

            }

        }
        return  false;
    };

}

