package com.jingang.lifechange.algorithm.struct.vector;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @Description:
 * 实现一个栈，要求 O(1) 开销, 支持以下操作:
 * push(val) 将 val 压入栈
 * pop() 将栈顶元素弹出, 并返回这个弹出的元素
 * peek() 获得栈顶元素
 * getMin() —— 获得栈中的最小元素。
 * getMax() —— 获得栈中的最大元素。
 * @Author: jingang.Li
 * @CreateTime: 2021/7/7-10:13 AM
 */
public class MinMaxStack  {
    /**
     * 实现基本思想，发现这个栈只是比普通栈 多了两个属性-记录当前最大值 和最小值
     * 如果我们可以把当前最大和最小值当做信息和当前push的值 进行包装后 放在第一个节点
     * 这样 压栈的时候 就会重新计算下最大最小，出栈的时候，也会把因为栈顶带来的变化给消除掉
     */
//    这里可以使用int[] 是因为int是原始数据类型，不继承子object 但是int[] 却是数组，继承自Object不是基本类型
    private final Stack<int[]> innerStack=new Stack<>();


    public synchronized void push(int val){
        if(!innerStack.isEmpty()){
            int max= Math.max(val, innerStack.peek()[1]);
            int min= Math.min(val, innerStack.peek()[2]);
            innerStack.push(new int[]{val,max,min});
        }else{
            innerStack.push(new int[]{val,val,val});
        }

    }
    public int pop(){
        return innerStack.pop()[0];
    }
    public int peek(){
        return innerStack.peek()[0];
    }
    public int getMax(){
        return innerStack.peek()[1];
    }
    public int getMin(){
        return innerStack.peek()[2];
    }
}
