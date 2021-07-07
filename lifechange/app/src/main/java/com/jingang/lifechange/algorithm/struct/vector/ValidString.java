package com.jingang.lifechange.algorithm.struct.vector;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Description: 给定一个字符串所表示的括号序列，包含以下字符： ‘(’, ‘)’, ‘{’, ‘}’, ‘[’ , ‘]’，
 * 判定是否是有效的括号序列。括号必须依照"()“顺序表示，”()[]{}“是有效的括号，”([)]"是无效的括号。
 * 输入:string = "([])(){}(())()()"
 * 输出: true
 * 此题 其实可以变种 ，随便插入一些字符 也不影响这个题的解法
 * @Author: jingang.Li
 * @CreateTime: 2021/7/6-11:18 AM
 */
class ValidString {
    public boolean checkStringValid(String input){
        char[] chars=input.toCharArray();
        Stack<Character> stack=new Stack<>();
//        HashMap<Character,Character> hashMap=new HashMap<>(5);
//        hashMap.put('(',')');
//        hashMap.put('{','}');
//        hashMap.put('[',']');
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='(' || chars[i]=='{' || chars[i]=='[' ){
                stack.push(chars[i]);
            }
            if (chars[i]==')' || chars[i]=='}' || chars[i]==']' ){
                if(stack.isEmpty() || Math.abs(chars[i]-stack.peek())>2){
                    return  false;
                }
                stack.pop();
//                if(chars[i]==hashMap.get(stack.peek())){
//                    stack.pop();
//                }

            }
        }
        return stack.isEmpty();
    }
}
