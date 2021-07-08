package com.jingang.lifechange.algorithm.struct.vector;

import android.util.Log;

/**
 * @Description:
 *汉诺塔(Tower of Hanoi)源于印度传说中，大梵天创造世界时造了三根金钢石柱子，
 * 其中一根柱子自底向上叠着64片黄金圆盘。大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。
 * 并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘。 求 怎么移动
 *
 * hannuota问题的形式化描述如下:假设有3个分别命名为X，Y，Z的塔座，在塔座X上插有n个直径大小各不相同，
 * 且从小到大编号为1、2、......、n的圆盘。现要求将塔座X上的n个圆盘借助塔座Y移至塔座Z上，并仍按同样顺序叠排。
 * 圆盘移动时必须遵守以下的规则:
 * 每次只能移动一个圆盘
 * 圆盘可以插在X、Y和Z中的任意一个塔座上
 * 任何时刻都不能将一个较大的圆盘压在较小的圆盘之上
 * @Author: jingang.Li
 * @CreateTime: 2021/7/8-9:58 AM
 */
class HanNuoTa {

    private final static String TAG=HanNuoTa.class.getSimpleName();
    public void getMoveStepTest(){
        String  x="Tower1";
        String  y="Tower2";
        String  z="Tower3";
        int n=3;//盘子数目
        printMoveStep(n,x,y,z);
    }
    private long times=0;

    /**
     *
     * @param n 当前盘子数目
     * @param x 基准塔名称
     * @param y 缓冲塔名称
     * @param z 目标塔名称
     */
    public void printMoveStep(int n, String x, String y, String z) {
        if(n==1){
//            这个log表明我们应该把x移动到z
            move(x,n,z);

        }else{
//            我们上面的n-1看做是一个整体，所以：
//            第一步是把n-1放到中间的缓冲区，以最后一个为缓冲塔
//            第二部是把最后一个盘子从x-移动到z
//            第三部是把中间的n-1从y移动到z 以第一个x为缓冲塔
            printMoveStep(n-1,x,z,y);
            move(x,n,z);
            printMoveStep(n-1,y,x,z);
        }
    }

    /**
     * 这个log表明我们应该把x移动到z
     * @param x
     * @param n
     * @param z
     */
    private void move(String x, int n, String z) {
        StringBuilder stringBuilder =new StringBuilder("move 盘子 ");
        stringBuilder.append(n).append(" from ").append(x).append(" to ").append(z)
                .append(" 当前是第").append(++times).append("次移动");
        Log.v(TAG,stringBuilder.toString());
    }


}
