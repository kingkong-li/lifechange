package com.jingang.lifechange.algorithm.struct.array;

import java.util.Arrays;

/**
 * @Description:
 * 给定一组学生的成绩，希望按照成绩给予奖励。并且给出的奖励最少。
 * 有两条规则：
 * 每个学生至少得到一份奖励
 * 成绩更高的学生必须得到严格高于相邻学生的奖励数目
 * 输入: [8, 4, 2, 1, 3, 6, 7, 9, 5]
 * 输出: 25
 * @Author: jingang.Li
 * @CreateTime: 2021/6/29-9:16 AM
 * @changeTime:
 */
class MinCandies {
    /**
     * 这道题的关键在于 把相邻获得奖励最高的分解为
     * 1 比左边大
     * 2 比右边大
     * 3 从左右中选择出一个最大的
     * 这道题之所以可以这样做，因为左边对当前值的要求分为两种
     * 一种是 右边比这个值大 要求会传递过来  二 右边比这个值小 要求完全无效
     * @param contribution
     * @return
     */
    public int getMinCandies(int[] contribution){
        if(contribution==null || contribution.length<1){
            return 0;
        }
      int[] leftToRightAwards=new int[contribution.length];
        Arrays.fill(leftToRightAwards,1);
        int[] rightToLeftAwards=new int[contribution.length];
        Arrays.fill(rightToLeftAwards,1);
        for(int i=1;i<contribution.length;i++){
            if(contribution[i]>contribution[i-1]){
                leftToRightAwards[i]=leftToRightAwards[i-1]+1;
            }
            if(contribution[i]==contribution[i-1]){
                leftToRightAwards[i]=leftToRightAwards[i-1];
            }
        }
        for(int j=contribution.length-2;j>=0;j--){
            if(contribution[j]>contribution[j+1]){
                rightToLeftAwards[j]=rightToLeftAwards[j+1]+1;
            }
            if(contribution[j]==contribution[j+1]){
                rightToLeftAwards[j]=rightToLeftAwards[j+1];
            }
        }
        int minCandies=0;
        for(int k=0;k<contribution.length;k++){
            minCandies=minCandies+Math.max(leftToRightAwards[k],rightToLeftAwards[k]);
        }

        return minCandies;
    }

}
