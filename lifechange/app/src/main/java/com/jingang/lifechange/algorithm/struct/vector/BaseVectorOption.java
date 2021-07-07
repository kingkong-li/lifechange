package com.jingang.lifechange.algorithm.struct.vector;

import java.util.Vector;

/**
 * @Description:
 * Vector 跟List类似，是通过数组实现的队列或者栈
 * Vector 与 List不同的地方在于 Vector方法 比如增加元素 和删除元素 都是同步的
 * 即 Vector是线程安全的 List不是
 * @Author: jingang.Li
 * @CreateTime: 2021/6/23-4:56 PM
 * @changeTime:
 */
class BaseVectorOption {
    Vector<Integer> vector=new Vector<>();
}
