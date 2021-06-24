package com.jingang.lifechange.innerclass;

import android.util.Log;

/**
 * @Description:
 * @Author: jingang.Li
 * @CreateTime:2021/6/24-4:35 PM
 * @changeTime:
 */
public class TestInnerClass {
    static int v = 1;
    static final class ClassA {
        int b = v;   // 成员变量初始化 第二执行 最后执行- b=2 v=2
        int a1;   // a1=0;
        int a2 = v++;   // a2=2 v=3
        static int a3 = v++;  // 静态代码最早执行--这时候a3=1;v=2;


        ClassA() {
            a1 = v++;  // 构造函数最后执行 v=4 a1=3；
        }
    }
    public static void printResult() {
        ClassA classA = new TestInnerClass.ClassA();


        Log.v("printResult","v=" + v); // 图中++3次 的确是4
        Log.v("printResult","b=" + classA.b); // 2 证明先执行构造函数- 执行方法时候 后才给
        // 成员变量赋值
        Log.v("printResult","a1=" + classA.a1); // 3
        Log.v("printResult","a2=" + classA.a2); // 2
        Log.v("printResult","a3=" + TestInnerClass.ClassA.a3); // 1 证明静态代码块 最先执行
    }


}
