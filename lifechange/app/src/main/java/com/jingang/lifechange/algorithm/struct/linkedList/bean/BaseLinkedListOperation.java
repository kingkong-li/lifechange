package com.jingang.lifechange.algorithm.struct.linkedList.bean;

import java.util.LinkedList;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/6/30-11:19 AM
 * @changeTime:
 */
class BaseLinkedListOperation {
    private static LinkedList<Integer> sLinkedList=new LinkedList<>();
    public static  void testBaseOperation(){
//        在链表尾部添加一个元素
        sLinkedList.add(1);
//        在链表头删除一个元素-出队列
        sLinkedList.poll();
//        上面两个方法构成了一个队列的基本操作

        sLinkedList.peekFirst();
        sLinkedList.peek();
        sLinkedList.pop();
        sLinkedList.push(2);

//       上面的方法构成一个stack的基本操作
        sLinkedList.size();

    }
}
