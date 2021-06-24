package com.jingang.lifechange.algorithm.struct.vector;

import android.os.Build;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * @Description:
 * 题目描述：
 * 给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 示例 1:
 * 输入: “bcabc”
 * 输出: “abc”
 * 示例 2:
 * 输入: “cbacdcbc”
 * 输出: “acdb”
 * 思考方向：
 * 重复与去重就应该想到hashset
 * 顺序相关的应该想到stack栈
 * 遇到复杂的问题一时想不出来就要考虑贪心算法。
 *
 * 贪心算法（又称贪婪算法）是指，在对问题求解时，总是做出在当前看来是最好的选择。
 * 也就是说，不从整体最优上加以考虑，他所做出的是在某种意义上的局部最优解。
 *
 * 本体思路：
 * 遍历所有字符，1.判定这个字符是否出现过，若没有先放入stack第一个字符，然后遍历从后面开始的字符
 * 2.如果这个字符比前一个字符要小，则检查前一个字符，若前一个字符在后面还会出现，则将前一个字符pop出栈
 * 再放入现在的字符，并且只要栈还有值，则一直重复这个流程检查，直到不符合要求
 * 3.放入现在的字符
 * @Author: jingang.Li
 * @CreateTime: 2021/6/23-5:27 PM
 */
class DuplicateLetters {

    public String removeDuplicateLetters(String inputString){
        // 构建HashMap存储每个字符出现的次数
        HashMap<Character, Integer> map=new HashMap<>(inputString.length());
        for (Character character:inputString.toCharArray()){
                int characterCount=1;
                if(map.get(character)!=null){
                    characterCount=map.get(character)+1;
                }
                map.put(character,characterCount);
        }
        // 接下来的set和stack存储的元素是一样的，set的唯一作用是确定当前stack中是否
//        已经存在即将遍历到的元素
        Stack<Character> stack=new Stack<>();
        HashSet<Character> set= new HashSet<>(inputString.length());
        for (Character character:inputString.toCharArray()){
            if(!set.contains(character)){
//                 如果不包含当前元素，入栈前 需要检查，栈顶元素是否比自己大 如果比自己大 而且
//                后面还有重复的 就要把栈顶出栈
                while (!stack.empty() && stack.peek()>character && map.get(stack.peek())>1){

                   map.put(stack.peek(),map.get(stack.peek()-1));
                   set.remove(stack.pop());
                }


                stack.push(character);
                set.add(character);
            }
        }
        StringBuilder stringBuilder=new StringBuilder();
        while (!stack.empty()){
           stringBuilder.append(stack.pop());
        }
        stringBuilder.reverse();
        return stringBuilder.toString();
    }
}
